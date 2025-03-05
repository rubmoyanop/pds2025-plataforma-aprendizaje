# Caso de Uso Extendido: Elegir un curso

## Actores

- **Usuario:** Quiere elegir un curso y realizar ciertos ejercicios sobre la temática escogida.
- **Sistema:** Ofrece cursos disponibles al usuario.

## Precondiciones

- El usuario debe haber elegido una estrategia de aprendizaje.

## Flujo principal

1. El usuario accede a la sección de cursos disponibles en la aplicación.
2. El sistema muestra una lista de cursos disponibles.
3. El usuario selecciona uno de los cursos.
4. El sistema muestra un ejercicio correspondiente al curso elegido.
5. El usuario selecciona el ejercicio.
6. El sistema muestra una pregunta sobre el curso escogido.
7. El usuario responde.
8. El sistema informa al usuario sobre si su respuesta es correcta o no.
9. El sistema vuelve al paso 6.

## Flujos alternativos

- **Se acaba el ejercicio:**
  9. El sistema muestra al usuario una pregunta que ha respondido incorrectamente para que la vuelva a responder.
  10. El usuario responde.
  11. El sistema informa al usuario sobre si su respuesta es correcta o no.
  12. El sistema vuelve al paso 9 si quedan respuestas incorrectas; si no, muestra una pantalla informando del resultado del ejercicio.
- **El usuario responde incorrectamente en una pregunta:**
  9. El sistema informa al usuario sobre cuál es la respuesta correcta.
  10. El sistema vuelve al paso 6.

## Postcondiciones

- El usuario desbloquea un nuevo ejercicio.
