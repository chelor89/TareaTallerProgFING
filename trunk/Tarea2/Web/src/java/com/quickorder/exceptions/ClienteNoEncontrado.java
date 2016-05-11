/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickorder.exceptions;

/**
 *
 * @author Igui
 */
public class ClienteNoEncontrado extends Exception {

	/**
	 * Creates a new instance of <code>UsuarioNoEncontrado</code> without detail message.
	 */
	public ClienteNoEncontrado() {
	}

	/**
	 * Constructs an instance of <code>UsuarioNoEncontrado</code> with the specified detail message.
	 * @param msg the detail message.
	 */
	public ClienteNoEncontrado(String msg) {
		super(msg);
	}
}
