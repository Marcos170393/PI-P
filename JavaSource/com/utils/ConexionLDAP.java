package com.utils;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class ConexionLDAP {

	public static void conexionLDAP(String username, String password) {
		String url = "ldap://APPSRV:389";
		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, url);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, username + "@UTEC.COM");
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			DirContext ctx = new InitialDirContext(env);
			System.out.println("Conectado");
			System.out.println(ctx.getEnvironment());
			ctx.close();
		} catch (AuthenticationNotSupportedException ex) {
			System.out.println("El servidor no admite autenticacion.");
		} catch (AuthenticationException ex) {
			System.out.println("Nombre de usuario o contraseña incorrectos.");
		} catch (NamingException ex) {
			System.out.println("Error al intentar crear el contexto.");
		}
	}

}
