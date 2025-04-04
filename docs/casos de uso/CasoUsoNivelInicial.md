[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
[![Ver todos los CdU](https://img.shields.io/badge/📋_Ver_todos_los_CdU-C62828?style=for-the-badge)](./CasosDeUso.md)

# Caso de Uso Extendido: Calcular nivel inicial

*Actor principal:* Estudiante.

*Personal involucrado e intereses:*

- Estudiante: Disponer de cursos acordes al nivel que posee inicialmente.
- Sistema: Conocer el nivel inicial del Estudiante para ofrecer cursos que se ajusten a este.

*Precondiciones:* El Estudiante elige una estrategia de aprendizaje que modele los ejercicios que va a realizar. El Estudiante elige un curso de entre los disponibles.

*Garantías de éxito (Postcondiciones):* Al Estudiante le es asignado un nivel inicial donde los ejercicios que realice le supongan un aprendizaje.

*Flujo básico:*

1. El Sistema ofrece una pantalla tras elegir un curso por primera vez donde aparezca la opción de no hacer prueba inicial o sí realizarla.
2. El Estudiante decide realizar una prueba inicial para conocer su nivel acerca del curso que va a realizar.
3. El Sistema comienza la prueba.
4. El Sistema muestra un ejercicio acorde a la estrategia de aprendizaje.
5. El Estudiante responde la pregunta.
6. El Sistema guarda la respuesta del Estudiante.

   El Sistema repite los pasos 4-6 hasta que se acabe la prueba.

7. El Sistema registra el total de respuestas correctas del Estudiante.
8. El Sistema calcula el nivel inicial en el que se encuentra el Estudiante.
9. El Sistema muestra al Estudiante su progreso en el curso.
10. El Estudiante realiza el siguiente ejercicio propuesto.

*Flujos alternativos:*

- 2a. El Estudiante decide no hacer prueba inicial. Le es asignado el nivel básico en ese curso.
- 10a. El Estudiante sale del curso y no realiza el siguiente ejercicio. Su progreso queda guardado.

---

[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
