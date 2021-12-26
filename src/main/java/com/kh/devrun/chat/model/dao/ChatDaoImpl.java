package com.kh.devrun.chat.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.devrun.chat.model.vo.ChatMember;
import com.kh.devrun.member.model.vo.Member;

@Repository
public class ChatDaoImpl implements ChatDao {

	@Autowired
	private SqlSession session;

	@Override
	public List<Member> selectAllMemberByNickname(Map<String, Object> param) {
		return session.selectList("chat.selectAllMemberByNickname", param);
	}

	@Override
	public ChatMember findChatMemberByMemberNo(Map<String, Object> param) {
		return session.selectOne("chat.findChatMemberByMemberNo", param);
	}

	@Override
	public int insertChatMember(ChatMember chatMember) {
		return session.insert("chat.insertChatMember", chatMember);
	}
}
