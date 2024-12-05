package com.shinhan.myapp.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shinhan.myapp.vo.MemberDTO;

import lombok.extern.slf4j.Slf4j;
import net.firstzone.util.UploadFileUtils;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 게시판 목록 조회
	@GetMapping("/list.do")
	public String boardList(Model model) {
		model.addAttribute("boardList", boardService.selectAll());
		return "board/boardList";
	}
	
	// 게시판 등록 화면 이동
	@GetMapping("/insert.do")
	public String boardInsert() {
		return "board/boardInsert";
	}
	
	// 게시판 등록
	@PostMapping("/insert.do")
	public String boardInsertPost(MultipartHttpServletRequest multipart, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginMember");
		if (memberDTO == null) {
			memberDTO = MemberDTO.builder().member_id("guest").build();
		}
		String writer = memberDTO.getMember_id();		

		String uploadPath = session.getServletContext().getRealPath("./resources/upload");
		MultipartFile multipartFile = multipart.getFile("pic");
		String fileName = multipartFile.getOriginalFilename(); // 이미지 이름
		String newfileName = "";
		String ymdPath = UploadFileUtils.calcPath(uploadPath); // 폴더 이름 및 폴더 생성
		try {
			// 파일 업로드
			newfileName = UploadFileUtils.fileUpload(uploadPath, fileName, multipartFile.getBytes(), ymdPath);
		} catch (Exception e) {
			e.printStackTrace();
			
			return "redirect:insert.do";
		}
		
		HttpServletRequest request = multipart;
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO boardDTO = BoardDTO.builder().title(title).content(content).build();
		
		boardDTO.setWriter(writer);
		boardDTO.setPic(ymdPath + File.separator + newfileName);
		log.info("boardDTO : " + boardDTO);
		
		boardService.insert(boardDTO);
		
		return "redirect:list.do";
	}
	
	// 게시판 상세 보기
	@GetMapping("/detail.do")
	public String boardDetail(Long boardNo, Model model) {
		model.addAttribute("boardDTO", boardService.selectById(boardNo));
		
		return "board/boardDetail";
	}
	
	// 게시판 수정
	@PostMapping("/detail.do")
	public String boardUpdate(Long boardNo, Model model) {
		model.addAttribute("boardDTO", boardService.selectById(boardNo));
		
		return "board/boardDetail";
	}

}
