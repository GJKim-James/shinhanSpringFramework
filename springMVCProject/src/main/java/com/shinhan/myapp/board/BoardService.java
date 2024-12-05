package com.shinhan.myapp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;

	// 게시판 전체 조회
	public List<BoardDTO> selectAll() {
		return boardRepository.selectAll();
	}

	// 특정 게시글 조회
	public BoardDTO selectById(Long boardNo) {
		return boardRepository.selectById(boardNo);
	}

	// 게시글 등록
	public int insert(BoardDTO boardDTO) {
		return boardRepository.insert(boardDTO);
	}

	// 게시글 수정
	public int update(BoardDTO boardDTO) {
		return boardRepository.update(boardDTO);
	}

	// 게시글 삭제
	public int delete(Long boardNo) {
		return boardRepository.delete(boardNo);
	}

}
