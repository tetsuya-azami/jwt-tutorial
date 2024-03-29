package com.example.jwttutorial.jwtAuthentication;

import com.example.jwttutorial.jwtAuthentication.authentication.SimpleIdProvider;
import com.example.jwttutorial.jwtAuthentication.authentication.UserAuthenticator;
import com.example.jwttutorial.jwtAuthentication.calculator.AddCalculator;
import com.example.jwttutorial.jwtAuthentication.calculator.AddOperator;
import com.example.jwttutorial.jwtAuthentication.tokenManipulator.AuthTokenProducer;
import com.example.jwttutorial.jwtAuthentication.tokenManipulator.AuthTokenVerifier;

public class ConsoleAppMain {
	public static void main(String[] args) {
		// トークンの生成
		String secretKey = System.getenv("SECRET_KEY");
		String id = args[0];
		String name = args[1];
		SimpleIdProvider idProvider =
				new SimpleIdProvider(new UserAuthenticator(), new AuthTokenProducer());

		String publishedToken = idProvider.publishToken(id, name);
		System.out.println(publishedToken);

		// トークンの検証と計算結果の返却
		int left = Integer.parseInt(args[2]);
		int right = Integer.parseInt(args[3]);
		String recievedToken = publishedToken;

		// oKPattern(left, right, recievedToken, secretKey);
		// illegalSecretKeyPattern(left, right, recievedToken);
		cheatedTokenPattern(left, right, recievedToken, secretKey);
	}

	public static void oKPattern(int left, int right, String token, String secretKey) {
		//
		AddCalculator calculator =
				new AddCalculator(new AuthTokenVerifier(secretKey), new AddOperator());
		calculator.calculate(left, right, token);
	}

	public static void illegalSecretKeyPattern(int left, int right, String token) {
		//
		String illegalSecretKey = "illegalSecretKey";

		AddCalculator calculator =
				new AddCalculator(new AuthTokenVerifier(illegalSecretKey), new AddOperator());
		calculator.calculate(left, right, token);
	}

	public static void cheatedTokenPattern(int left, int right, String token , String secretKey) {
		//
		String cheatedToken = token.substring(1);
		System.out.println(cheatedToken);
		AddCalculator calculator =
				new AddCalculator(new AuthTokenVerifier(secretKey), new AddOperator());
		calculator.calculate(left, right, cheatedToken);
	}
}
