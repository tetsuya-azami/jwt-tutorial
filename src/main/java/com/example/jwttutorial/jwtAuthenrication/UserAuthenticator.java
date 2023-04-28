package com.example.jwttutorial.jwtAuthenrication;

import java.util.List;

public class UserAuthenticator {

	private static final List<User> USERS = List.of(new User("soramame", "emamaros", "そら豆 太郎"),
			new User("edamame", "emamade", "えだ豆 次郎"), new User("kuromame", "emamoruk", "くろ豆 三郎"));

			public User authenticate(String id, String password) throws IllegalArgumentException {
				//
				return USERS.stream().filter(user -> user.getId().equals(id)).filter(user -> user.getPassword().equals(password)).findFirst().orElseThrow(() -> new IllegalArgumentException("unkown user."));
			}
}