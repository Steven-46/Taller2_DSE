package com.ecodeup.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ecodeup.model.Control;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		Control control;

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		while (opcion!=5) {
			System.out.println("1. Ingresar documento");
			System.out.println("2. Buscar documento");
			System.out.println("3. Actualizar documento");
			System.out.println("4. Eliminar documento");
			System.out.println("5. Salir");
			System.out.println("Elija una opción:");

			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Procedencia del documento:");
				control = new Control();
				control.setId(null);
				scanner.nextLine();
				control.setProcedencia(scanner.nextLine());
				
				System.out.println("Fecha del documento:");
				control.setFecha(scanner.nextLine());
				
				System.out.println("Codigo del documento:");
				control.setCodigo(scanner.nextLine());
				
				System.out.println("Asunto del documento:");
				control.setAsunto(scanner.nextLine());
				
				System.out.println("Estante donde se encuentra el documento:");
				control.setEstante(scanner.nextInt());
				
				System.out.println("Caja donde se encuentra el documento:");
				control.setCaja(scanner.nextInt());
				
				System.out.println();
				System.out.println(control);
				
				entity.getTransaction().begin();
				entity.persist(control);
				entity.getTransaction().commit();
				
				System.out.println("Documento registrado..");
				System.out.println();
				break;

			case 2:
				System.out.println("Digite el id del documento a buscar:");
				control = new Control();
				control = entity.find(Control.class, scanner.nextLong());
				if (control != null) {
					System.out.println(control);
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Documento no encontrado... Lista de documentos completa");
					List<Control> listaControles= new ArrayList<>();
					Query query=entity.createQuery("SELECT c FROM Control c");
					listaControles=query.getResultList();
					for (Control c : listaControles) {
						System.out.println(c);
					}
					
					System.out.println();
				}

				break;
			case 3:
				System.out.println("Digite el id del documento a actualizar:");
				control = new Control();

				control = entity.find(Control.class, scanner.nextLong());
				if (control != null) {
					System.out.println(control);
					
					System.out.println("Procedencia del documento:");
					scanner.nextLine();
					control.setProcedencia(scanner.nextLine());
					System.out.println("Fecha del documento:");
					control.setFecha(scanner.nextLine());					
					System.out.println("Codigo del documento:");
					control.setCodigo(scanner.nextLine());					
					System.out.println("Asunto del documento:");
					control.setAsunto(scanner.nextLine());					
					System.out.println("Estante donde se encuentra el documento:");
					control.setEstante(scanner.nextInt());					
					System.out.println("Caja donde se encuentra el documento:");
					control.setCaja(scanner.nextInt());
					
					entity.getTransaction().begin();
					entity.merge(control);
					entity.getTransaction().commit();
					System.out.println("Documento actualizado..");
					System.out.println();
				} else {
					System.out.println("Documento no encontrado....");
					System.out.println();
				}
				break;
			case 4:
				System.out.println("Digite el id del documento a eliminar:");
				control = new Control();

				control = entity.find(Control.class, scanner.nextLong());
				if (control != null) {
					System.out.println(control);
					entity.getTransaction().begin();
					entity.remove(control);
					entity.getTransaction().commit();
					System.out.println("Documento eliminado...");
				} else {
					System.out.println("Documento no encontrado...");
				}
				break;
			case 5:entity.close();JPAUtil.shutdown();
			break;

			default:
				System.out.println("Opción no válida\n");
				break;
			}
		}
	}

}
