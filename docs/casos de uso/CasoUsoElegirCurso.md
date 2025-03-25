# Caso de Uso Extendido: Elegir un curso

## Actores

- **Estudiante:** Quiere elegir un curso y realizar ciertos ejercicios sobre la temática escogida.
- **Sistema:** Ofrece cursos disponibles al Estudiante.

## Precondiciones

- El Estudiante debe haber elegido una estrategia de aprendizaje.

## Flujo principal

1. El Estudiante accede a la sección de cursos disponibles en la aplicación.
2. El Sistema muestra una lista de cursos disponibles.
3. El Estudiante selecciona uno de los cursos.
4. El Sistema muestra el bloque de ejercicios correspondiente al curso elegido.
5. El Estudiante selecciona el bloque.
6. El Sistema muestra el ejercicio correspondiente.
7. El Estudiante responde.
8. El Sistema informa al Estudiante sobre si su respuesta es correcta o no.
9. El Sistema vuelve al paso 6.

## Flujos alternativos

- **Se acaba el ejercicio:**
  9. El Sistema muestra al Estudiante un ejercicio que ha respondido incorrectamente para que lo vuelva a responder.
  10. El Estudiante responde.
  11. El Sistema informa al Estudiante sobre si su respuesta es correcta o no.
  12. El Sistema vuelve al paso 9 si quedan respuestas incorrectas; si no, muestra una pantalla informando del resultado del ejercicio.
- **El Estudiante responde incorrectamente en un ejercicio:**
  9. El Sistema informa al Estudiante sobre cuál es la respuesta correcta.
  10. El Sistema vuelve al paso 6.

## Postcondiciones

- El Estudiante desbloquea un nuevo ejercicio.
