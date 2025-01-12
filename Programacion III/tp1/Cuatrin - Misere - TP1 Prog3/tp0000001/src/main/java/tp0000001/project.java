package tp0000001;

import java.util.ArrayList;
import com.github.javafaker.Faker;

public class project {

	public static void main(String[] args) {
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>();
		System.out.println("Generar Alumnos");
		CargarAlumnos(listaAlumnos, 50);
		for (Alumno unAlumno : listaAlumnos) {
			System.out.println(unAlumno.toString());
		}
		System.out.println("\nGenerar Profesores");
		CargarProfesores(listaProfesores, 50);
		for (Profesor unProfesor : listaProfesores) {
			System.out.println(unProfesor.toString());
		}
		System.out.println("\nOrdenar Alumnos por Apellido y Nombre");
		listaAlumnos.sort(null);
		for (Alumno unAlumno : listaAlumnos) {
			System.out.println(unAlumno.toString());
		}
		System.out.println("\nOrdenar Profesores por legajo");
		listaProfesores.sort(null);
		for (Profesor unProfesor : listaProfesores) {
			System.out.println(unProfesor.toString());
		}
		System.out.println("\nMayor Promedio de la lista");
		System.out.println(BuscarMayorPromedio(listaAlumnos).toString());
		System.out.println("\nMenor Promedio de la lista");
		System.out.println(BuscarMenorPromedio(listaAlumnos).toString());
		System.out.println("\nLista de Alumnos con Promedio Mayor a 7");
		for (Alumno unAlumno : AlumnosMayorPromedio(listaAlumnos)) {
			System.out.println(unAlumno.toString());
		}
		System.out.println("\nLista de Profesores Ingenieros");
		for (Profesor unProfesor : ProfesoresIngenieros(listaProfesores)) {
			System.out.println(unProfesor.toString());
		}
		System.out.println("\nLista de Profes por Edad");
		for (Profesor unProfesor : OrdenaProfesoresPorEdad(listaProfesores)) {
			System.out.println(unProfesor.toString());
		}
		System.out.println("\nSuma de Edades de los profes");
		System.out.println(TotalEdadProfesores(listaProfesores) + " años");
		System.out.println("\nPromedio Total y Mejores de Alumnos");
		float[] promedios = new float[2];
		promedios = CalcularPromedios(listaAlumnos);
		System.out.println("\nPromedio Total es: " + promedios[0]);
		System.out.println("\nPromedio Mejores es: " + promedios[1]);
	}

	public static void CargarAlumnos(ArrayList<Alumno> listaAlumnos, int cantidad) {
		Alumno unAlumno;
		Faker unFaker = new Faker();
		int id, edad, dni, nroLE, materiasAprobadas;
		float promedio;
		String nombre, apellido;
		for (int i = 0; i < cantidad; i++) {
			id = unFaker.number().numberBetween(0, 99999);
			edad = unFaker.number().numberBetween(18, 100);
			nombre = unFaker.name().firstName();
			apellido = unFaker.name().lastName();
			nroLE = id;
			dni = id = unFaker.number().numberBetween(1000000, 50000000);
			materiasAprobadas = unFaker.number().numberBetween(0, 15);
			promedio = (float) unFaker.number().randomDouble(2, 1, 10);
			unAlumno = new Alumno(id, edad, dni, nombre, apellido, nroLE, materiasAprobadas, promedio);
			listaAlumnos.add(unAlumno);
		}
	}

	public static void CargarProfesores(ArrayList<Profesor> listaProfesores, int cantidad) {
		String[] titulos = { "Ing. en Sistemas", "Licenciado en Inf.", "Programador" };
		Profesor unProfesor;
		Faker unFaker = new Faker();
		int id, edad, dni, nroLE;
		String nombre, apellido, titulo;
		for (int i = 0; i < cantidad; i++) {
			id = unFaker.number().numberBetween(0, 99999);
			edad = unFaker.number().numberBetween(18, 100);
			nombre = unFaker.name().firstName();
			apellido = unFaker.name().lastName();
			dni = id = unFaker.number().numberBetween(1000000, 50000000);
			nroLE = id;
			titulo = titulos[unFaker.number().numberBetween(0, 2)];
			unProfesor = new Profesor(id, edad, dni, nombre, apellido, nroLE, titulo);
			listaProfesores.add(unProfesor);
		}
	}

	public static Alumno BuscarMenorPromedio(ArrayList<Alumno> listaAlumnos) {
		Alumno mejorAlumno = listaAlumnos.get(0);
		for (Alumno a : listaAlumnos) {
			if (mejorAlumno.getPromedio() > a.getPromedio()) {
				mejorAlumno = a;
			}
		}
		return mejorAlumno;
	}

	public static Alumno BuscarMayorPromedio(ArrayList<Alumno> listaAlumnos) {
		Alumno peorAlumno = listaAlumnos.get(0);
		for (Alumno a : listaAlumnos) {
			if (peorAlumno.getPromedio() < a.getPromedio()) {
				peorAlumno = a;
			}
		}
		return peorAlumno;
	}

	public static Alumno[] AlumnosMayorPromedio(ArrayList<Alumno> listaAlumnos) {
		Alumno[] mejoresAlumnos = new Alumno[listaAlumnos.size()];
		Alumno AlumnoAuxiliar = null;
		int cantMejores = 0;
		for (Alumno a : listaAlumnos) {
			if (a.getPromedio() > 7) {
				mejoresAlumnos[cantMejores] = a;
				cantMejores++;
			}
		}
		for (int i = 0; i < cantMejores - 1; i++) {
			for (int j = i; j < cantMejores; j++) {
				if (mejoresAlumnos[i].getPromedio() > mejoresAlumnos[j].getPromedio()) {
					AlumnoAuxiliar = mejoresAlumnos[i];
					mejoresAlumnos[i] = mejoresAlumnos[j];
					mejoresAlumnos[j] = AlumnoAuxiliar;
				}
			}
		}
		Alumno[] mejoresAlumnosDevolver = new Alumno[cantMejores];
		int i = 0;
		boolean end = false;
		do {
			if (mejoresAlumnos[i] != null) {
				mejoresAlumnosDevolver[i] = mejoresAlumnos[i];
			} else {
				end = true;
			}
			i++;
		} while (i < cantMejores && !end);
		return mejoresAlumnosDevolver;
	}

	public static ArrayList<Profesor> ProfesoresIngenieros(ArrayList<Profesor> listProfesores) {
		ArrayList<Profesor> listIngenieros = new ArrayList<Profesor>();
		for (Profesor unProfesor : listProfesores) {
			if (unProfesor.getTitulo() == "Ing. en Sistemas") {
				listIngenieros.add(unProfesor);
			}
		}
		return listIngenieros;
	}

	public static Profesor[] OrdenaProfesoresPorEdad(ArrayList<Profesor> listaProfesores) {
		Profesor[] profesPorEdad = new Profesor[listaProfesores.size()];
		int cont = 0;
		for (Profesor unProfesor : listaProfesores) {
			profesPorEdad[cont] = unProfesor;
			cont++;
		}
		Profesor ProfesorAuxiliar = null;
		for (int i = 0; i < cont - 1; i++) {
			for (int j = i; j < cont; j++) {
				if (profesPorEdad[i].getEdad() < profesPorEdad[j].getEdad()) {
					ProfesorAuxiliar = profesPorEdad[i];
					profesPorEdad[i] = profesPorEdad[j];
					profesPorEdad[j] = ProfesorAuxiliar;
				}
			}
		}
		return profesPorEdad;
	}

	public static int TotalEdadProfesores(ArrayList<Profesor> listProfesores) {
		int total = 0;
		for (Profesor unProfesor : listProfesores) {
			total += unProfesor.getEdad();
		}
		return total;
	}

	public static float[] CalcularPromedios(ArrayList<Alumno> listAlumnos) {
		float[] promedios = new float[2];
		Alumno[] listMejoresAlumnos = new Alumno[AlumnosMayorPromedio(listAlumnos).length];
		promedios[0] = 0;
		promedios[1] = 0;
		listMejoresAlumnos = AlumnosMayorPromedio(listAlumnos);
		for (Alumno unAlumno : listAlumnos) {
			promedios[0] += unAlumno.getPromedio();
		}
		for (Alumno unAlumno : listMejoresAlumnos) {
			promedios[1] += unAlumno.getPromedio();
		}
		promedios[0] = promedios[0] / listAlumnos.size();
		promedios[1] = promedios[1] / listMejoresAlumnos.length;
		return promedios;
	}
}
