package com.shinhan.myapp.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {

	@Autowired
	SqlSession sqlSession;

	String namespace = "com.shinhan.board.";

	// �Խ��� ��ü ��ȸ
	public List<BoardDTO> selectAll() {
		return sqlSession.selectList(namespace + "selectAll");
	}

	// Ư�� �Խñ� ��ȸ
	public BoardDTO selectById(Long boardNo) {
		return sqlSession.selectOne(namespace + "selectById", boardNo);
	}

	// �Խñ� ���
	public int insert(BoardDTO boardDTO) {
		return sqlSession.insert(namespace + "insert", boardDTO);
	}

	// �Խñ� ����
	public int update(BoardDTO boardDTO) {
		return sqlSession.update(namespace + "update", boardDTO);
	}

	// �Խñ� ����
	public int delete(Long boardNo) {
		return sqlSession.delete(namespace + "delete", boardNo);
	}

}
