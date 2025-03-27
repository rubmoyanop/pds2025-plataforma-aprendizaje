# Caso de Uso Extendido: Elegir un curso

## Actores

- **Estudiante:** Quiere elegir un curso y realizar ciertos ejercicios sobre la temática escogida.
- **Sistema:** Ofrece cursos disponibles al Estudiante.

## Precondiciones
 
 - El Estudiante debe estar registrado.

## Flujo principal

1. El Estudiante accede a la sección de cursos disponibles en la aplicación.
2. El Sistema muestra una lista de cursos disponibles.
3. El Estudiante selecciona uno de los cursos.
4. El Estudiante escoge una estrategia de aprendizaje.
5. El Sistema muestra el bloque de ejercicios correspondiente al curso elegido.
6. El Estudiante inicia el curso.
7. El Sistema muestra el ejercicio correspondiente.
8. El Estudiante responde.
9. El Sistema informa al Estudiante sobre si su respuesta es correcta o no.
10. El Sistema vuelve al paso 7.

## Flujos alternativos

- **7A No hay más ejercicios disponibles en el bloque:**
  1. El Sistema devuelve al Estudiante a la pantalla de detalles del curso.

## Postcondiciones

- El Estudiante desbloquea un nuevo ejercicio.
