# Caso de Uso Extendido: Dar recompensa

*Actor principal:* Sistema.

*Personal involucrado e intereses:*

- Estudiante: Recibir recompensas tras completar un curso.
- Sistema: Recompensar al Estudiante para que siga completando cursos.

*Precondiciones:* El Estudiante inicia sesión. El Estudiante completa un curso.

*Garantías de éxito (Postcondiciones):* El Estudiante recibe una recompensa por su aprendizaje.

*Flujo básico:*

1. El Estudiante completa el último bloque correspondiente a un curso.
2. El Sistema muestra una pantalla de curso completado.
3. El Sistema calcula con una función de aleatoriedad si dar recompensa al Estudiante
4. Si sale favorable, el Sistema otorga una recompensa al Estudiante.
5. El Sistema muestra una pantalla indicando la recompensa obtenida por el Estudiante.
6. El Estudiante pulsa Aceptar.
7. El Sistema redirige al Estudiante a la pantalla de cursos.


*Flujos alternativos:*

- 4a. No sale favorable. El Estudiante finaliza el curso sin recompensa. Se le redirige a la pantalla de cursos.
