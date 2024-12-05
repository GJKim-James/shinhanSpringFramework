package com.shinhan.myapp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;

	// �Խ��� ��ü ��ȸ
	public List<BoardDTO> selectAll() {
		return boardRepository.selectAll();
	}

	// Ư�� �Խñ� ��ȸ
	public BoardDTO selectById(Long boardNo) {
		return boardRepository.selectById(boardNo);
	}

	// �Խñ� ���
	public int insert(BoardDTO boardDTO) {
		return boardRepository.insert(boardDTO);
	}

	// �Խñ� ����
	public int update(BoardDTO boardDTO) {
		return boardRepository.update(boardDTO);
	}

	// �Խñ� ����
	public int delete(Long boardNo) {
		return boardRepository.delete(boardNo);
	}

}
