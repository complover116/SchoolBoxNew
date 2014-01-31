package com.complover116.SchoolBox;

public class Question {
 String question = "��������� ������. ��������� ���������� �������� �������, ��� �� ���� �� ����� ����. ��������, ������� �� ����� 1 �������� ��.";
 String pans1 = "������� ����";
 String pans2 = "";
 String pans3 = "";
 String pans4 = "";
 int type;
 int rans = 1;
	public Question(String q, String pan1, String pan2, String pan3, String pan4, int rians, int typ) {
		question =q;
		// TODO Auto-generated constructor stub
		pans1 = pan1;
		pans2 = pan2;
		pans3 = pan3;
		pans4 = pan4;
		rans = rians;
		type = typ;
	}
}