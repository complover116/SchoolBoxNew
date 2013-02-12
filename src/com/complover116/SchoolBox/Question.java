package com.complover116.SchoolBox;

public class Question {
 String question = "Произошла ошибка. Обьявлено количество вопросов большее, чем их есть на самом деле. Возможно, нажатие на ответ 1 исправит её.";
 String pans1 = "Нажмите сюда";
 String pans2 = "";
 String pans3 = "";
 String pans4 = "";
 int rans = 1;
	public Question(String q, String pan1, String pan2, String pan3, String pan4, int rians) {
		question =q;
		// TODO Auto-generated constructor stub
		pans1 = pan1;
		pans2 = pan2;
		pans3 = pan3;
		pans4 = pan4;
		rans = rians;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
