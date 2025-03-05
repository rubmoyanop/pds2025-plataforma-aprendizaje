# Caso de Uso: Calcular nivel inicial

*Actor principal:* Usuario.

*Personal involucrado e intereses:*
- Usuario: Disponer de cursos acordes al nivel que posee inicialmente.
- Sistema: Conocer el nivel inicial del Usuario para ofrecer cursos que se ajusten a este.

*Precondiciones:* El Usuario elige una estrategia de aprendizaje que modele los ejercicios que va a realizar. El Usuario elige un curso de entre los disponibles.

*Garantías de éxito (Postcondiciones):* Al Usuario le es asignado un nivel inicial donde los ejercicios que realice le supongan un aprendizaje.

*Flujo básico:*
1. El Sistema ofrece una pantalla tras elegir un curso por primera vez donde aparezca la opción de no hacer prueba inicial o sí realizarla.
2. El Usuario decide realizar una prueba inicial para conocer su nivel acerca del curso que va a realizar.
3. El Sistema comienza la prueba.
4. El Sistema muestra un ejercicio acorde a la estrategia de aprendizaje.
5. El Usuario responde la pregunta.
6. El Sistema guarda la respuesta del Usuario.

   El Sistema repite los pasos 4-6 hasta que se acabe la prueba.

7. El Sistema registra el total de respuestas correctas del Usuario.
8. El Sistema calcula el nivel inicial en el que se encuentra el Usuario.
9. El Sistema muestra al Usuario su progreso en el curso.
10. El Usuario realiza el siguiente ejercicio propuesto.

*Flujos alternativos:*
- 2a. El Usuario decide no hacer prueba inicial. Le es asignado el nivel básico en ese curso.
- 10a. El Usuario sale del curso y no realiza el siguiente ejercicio. Su progreso queda guardado.