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

	// 게시판 전체 조회
	public List<BoardDTO> selectAll() {
		return sqlSession.selectList(namespace + "selectAll");
	}

	// 특정 게시글 조회
	public BoardDTO selectById(Long boardNo) {
		return sqlSession.selectOne(namespace + "selectById", boardNo);
	}

	// 게시글 등록
	public int insert(BoardDTO boardDTO) {
		return sqlSession.insert(namespace + "insert", boardDTO);
	}

	// 게시글 수정
	public int update(BoardDTO boardDTO) {
		return sqlSession.update(namespace + "update", boardDTO);
	}

	// 게시글 삭제
	public int delete(Long boardNo) {
		return sqlSession.delete(namespace + "delete", boardNo);
	}

}
