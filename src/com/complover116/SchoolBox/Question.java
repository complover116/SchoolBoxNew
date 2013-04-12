package com.complover116.SchoolBox;

public class Question {
 String question = "An error occured. Please click answer 1. It will countas a right answer."
 String pans1 = "CLICK ME"
 String pans2 = "";
 String pans3 = "";
 String pans4 = "";
 String type = "";
 int rans = 1;
	public Question(String q, String pan1, String pan2, String pan3, String pan4, int rians, String typ) {
		question =q;
		pans1 = pan1;
		pans2 = pan2;
		pans3 = pan3;
		pans4 = pan4;
		rans = rians;
		type = typ
	}
}
