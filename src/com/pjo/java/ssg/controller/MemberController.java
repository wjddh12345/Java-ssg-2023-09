package com.pjo.java.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.pjo.java.ssg.dto.Member;
import com.pjo.java.ssg.util.Util;

public class MemberController {
	private List<Member> members;
	private Scanner sc;
	
	public MemberController(Scanner sc, List<Member> members) {
		this.members = members;
		this.sc = sc;
	}
	
	public void doJoin() {
		int id = members.size() + 1;
		String regDate = Util.getNowDateStr();

		String loginId = null;
		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isjoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}

		while (true) {
			System.out.printf("로그인 비번 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비번확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}

			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 생성되었습니다. 환영합니다^^\n", id);
		
	}
	
	private boolean isjoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}

			i++;
		}

		return -1;
	}


}