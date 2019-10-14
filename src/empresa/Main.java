package empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String cadConexion = "jdbc:mysql://localhost:3306/";
		String bd = "empresa";
		String usuario = "root";
		String pass = "1234";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(cadConexion + bd, usuario, pass);
			Statement stmt;
			PreparedStatement pstmt;
			ResultSet rs;
			Scanner entrada = new Scanner(System.in);
			int res, id, opc,opc1;
			float precio;
			String nombre;
			do {
				System.out.println("con que tabla quieres tratar");
				System.out.println("1.- Productos");
				System.out.println("2.- Proveedor");
				System.out.println("0.- Salir");
				res = Integer.parseInt(entrada.nextLine());
				if (res == 1) {	// PRODUCTO
					do {
					System.out.println("Has escojido Productos, que deseas hacer en:");
					System.out.println("1. Ver registros");
					System.out.println("2. Añadir registro");
					System.out.println("3. Modificar registro");
					System.out.println("4. Eliminar registro");
					System.out.println("0. Volver");
					opc = Integer.parseInt(entrada.nextLine());
					switch (opc) {
					case 1: // Ver 
						stmt = con.createStatement();
						rs = stmt.executeQuery("select * from producto");																					
						while (rs.next())
							System.out.println(rs.getInt("IDProducto") + " // " + rs.getString("Nombre") + " // " +  rs.getFloat("PrecioUd"));
						break;
					
					case 2: // Añadir 
						System.out.print("Introduzca el nombre del nuevo producto: ");
						nombre = entrada.nextLine();
						System.out.print("Introduzca el precio del producto: ");
						precio =Float.parseFloat(entrada.nextLine());
						pstmt = con.prepareStatement("insert into producto (Nombre, PrecioUd) values(?,?)");
						pstmt.setString(1, nombre);
						pstmt.setFloat(2, precio);
						pstmt.executeUpdate();
						System.out.println("El producto ha sido añadido");
						break;
					
					case 3: // Modificar
						System.out.print("Introduzca el ID del producto para modificarlo: ");
						id = Integer.parseInt(entrada.nextLine());
						System.out.print("Introduzca el nuevo nombre del producto: ");
						nombre = entrada.nextLine();
						System.out.print("Introduzca el nuevo precio del producto: ");
						precio =Float.parseFloat(entrada.nextLine());
						pstmt = con.prepareStatement("update producto set Nombre=?, PrecioUd=? where IDProducto=?");
						pstmt.setString(1, nombre);
						pstmt.setFloat(2, precio);
						pstmt.setInt(3, id);
						pstmt.execute();
						System.out.println("El producto se ha modificado");
						break;
					
					case 4: // Eliminar 
						System.out.print("Introduzca el ID del producto para eliminarlo: ");
						id = Integer.parseInt(entrada.nextLine());
						pstmt = con.prepareStatement("delete from producto where IDProducto=?");
						pstmt.setInt(1, id);
						pstmt.execute();
						System.out.println("el registro se ha eliminado");
						break;
					}//swith producto
					} while (opc != 0);
					
					}// if productos
				
				if (res == 2) {	// PROVEEDORES
					
					
					String NIF;
					String direccion;
					do {
						System.out.println("Has escojido Proveedores, que deseas hacer en:");
						System.out.println("1. Ver registros");
						System.out.println("2. Añadir registro");
						System.out.println("3. Modificar registro");
						System.out.println("4. Eliminar registro");
						System.out.println("0. Volver");
						opc1 = Integer.parseInt(entrada.nextLine());
					
						switch (opc1) {
						case 1: // Ver registros
							stmt = con.createStatement();
							rs = stmt.executeQuery("select * from proveedor");
							while (rs.next())
								System.out.println(rs.getInt("IDProveedor") + " // " + rs.getString("NIF") + " // " + rs.getString("Nombre") + " // " + rs.getString("Direccion"));
							break;
						case 2: // Añadir registro
							System.out.print("Introduzca el nombre del nuevo proveedor: ");
							nombre = entrada.nextLine();
							System.out.print("Introduzca el NIF del nuevo proveedor: ");
							NIF = entrada.nextLine();
							System.out.print("Introduzca la dirección del nuevo proveedor: ");
							direccion = entrada.nextLine();
							pstmt = con.prepareStatement("insert into proveedor (NIF, Nombre, Direccion) values(?,?,?)");
							pstmt.setString(1, NIF);
							pstmt.setString(2, nombre);
							pstmt.setString(3, direccion);
							pstmt.executeUpdate();
							System.out.println("El proveedor se ha añadido");
							break;
							
						case 3: // Modificar registro
							System.out.print("Introduzca ID del proveedor que se quiere modificar: ");
							id = Integer.parseInt(entrada.nextLine());
							System.out.print("Introduzca el nuevo NIF: ");
							NIF = entrada.nextLine();
							System.out.print("Introduzca el nuevo nombre: ");
							nombre = entrada.nextLine();
							System.out.print("Introduzca la nueva dirección: ");
							direccion = entrada.nextLine();
							pstmt = con.prepareStatement("update proveedor set NIF=?, Nombre=?, Direccion=? where IDProveedor=?");
							pstmt.setString(1, NIF);
							pstmt.setString(2, nombre);
							pstmt.setString(3, direccion);
							pstmt.setInt(4, id);
							pstmt.execute();
							System.out.println("el proveedor se ha modificado");
							break;
						case 4: // Eliminar registro
							System.out.print("Introduzca el ID del proveedor para eliminarlo: ");
							id = Integer.parseInt(entrada.nextLine());
							pstmt = con.prepareStatement("delete from proveedor where IDProveedor=?");
							pstmt.setInt(1, id);
							pstmt.execute();
							System.out.println("El proveedor se ha eliminado eliminado.");
							break;
						
						
						}
					} while (opc1 != 0);
				}
				if (res == 0)
					break;	
					
		
					}//do
		
			while (res != 0);
			con.close();
			entrada.close();
	}//try
		catch (Exception e) {
			System.out.println(e);
		}
	
	}	
}
