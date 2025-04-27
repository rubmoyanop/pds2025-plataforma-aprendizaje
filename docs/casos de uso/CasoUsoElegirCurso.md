[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
[![Ver todos los CdU](https://img.shields.io/badge/📋_Ver_todos_los_CdU-C62828?style=for-the-badge)](./CasosDeUso.md)

# Caso de Uso Extendido: Elegir un curso

## Actores

- **Estudiante:** Principal. Quiere elegir un curso y realizar ciertos ejercicios sobre la temática escogida.
- **Sistema:** Ofrece cursos disponibles al Estudiante.

## Precondiciones
 
 - El Estudiante debe estar registrado y con sesión iniciada.

## Flujo principal

1. El Estudiante accede a la sección de cursos disponibles en la aplicación.
2. El Sistema muestra una lista de cursos disponibles.
3. El Estudiante selecciona uno de los cursos.
4. El Sistema muestra una pantalla con los detalles del curso.
5. El Estudiante se inscribe al curso si no lo está ya.
6. El Sistema actualiza el estado a inscrito y permite realizar el primer bloque.
7. El Estudiante selecciona el primer bloque del curso.
8. El Sistema comienza el bloque.
9. El Estudiante escoge una estrategia de aprendizaje.
10. El Sistema muestra el ejercicio correspondiente.
11. El Estudiante responde.
12. El Sistema informa al Estudiante sobre si su respuesta es correcta o no.

  Se repiten los pasos 8-12 hasta que se acaben los ejercicios del bloque

13. El Sistema guarda el progreso del curso con un nuevo bloque completado.
14. El Sistema vuelve a la pantalla de detalles del curso.

## Flujos alternativos

- **3A El Estudiante se desinscribe de un curso en progreso**
  1. El Sistema indica una advertencia de que ese curso ya está en progreso.
  2. El Sistema borra el progreso de ese curso si el Estudiante lo autoriza.
- **5A El Estudiante no se ha inscrito previamente:**
  1. El Sistema indica que no se puede realizar ningún bloque hasta estar inscrito en el curso.
- **6A El Estudiante selecciona un curso que no es el que le toca**
  1. El Sistema indica que primero debe realizar los bloques anteriores.
- **13A El Estudiante no completa todos los ejercicios del bloque**
  1. El Sistema no guarda ningún progreso nuevo.  
- **14A No hay más bloques disponibles en el curso:**
  1. El Sistema registra el curso como completado.
  2. El Sistema devuelve al Estudiante a la pantalla de detalles del curso.

## Postcondiciones

- El Estudiante avanza en el progreso de un curso.

---

[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
