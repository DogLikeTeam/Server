package cn.tedu.meta.utils;

import org.apache.shiro.crypto.hash.Md5Hash;



public class Md5HashPassword {
	public static String getMd5Hash(String username,String password){
		Md5Hash md5Hash =new Md5Hash(username,password,3);
		return md5Hash.toString();
	}

//	public static void main(String[] args) {
//		//source:明文    salt 盐    hashIterations 哈希的次数
//		Md5Hash md5Hash=new Md5Hash("tew","12345",3);
//		System.out.println(md5Hash);
//	}

}
