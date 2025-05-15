[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
[![Ver todos los CdU](https://img.shields.io/badge/📋_Ver_todos_los_CdU-C62828?style=for-the-badge)](./CasosDeUso.md)

# Caso de Uso Extendido: Dar experiencia

## Actores

- **Sistema:** Principal. Recompensa al Estudiante para que siga completando bloques.
- **Estudiante:** Recibe experiencia tras completar un bloque en un curso.

## Precondiciones

- El Estudiante debe haber iniciado sesión.
- El Estudiante debe estar inscrito en un curso.

## Flujo principal

1. El Estudiante selecciona y completa un bloque correspondiente a un curso.
2. El Sistema muestra una pantalla de bloque completado donde se visualiza la experiencia obtenida.
3. El Sistema actualiza las estadísticas del Estudiante.
4. El Estudiante pulsa "Aceptar".
5. El Sistema redirige al Estudiante a la pantalla de bloques del curso.

## Flujos alternativos

- **1A El Estudiante no finaliza el bloque:**
  1. El Sistema redirige al Estudiante a la pantalla de bloques del curso sin otorgar recompensa.
-   **3A Error al actualizar las estadísticas:**
  1.  El Sistema muestra un mensaje de error al Estudiante.

## Postcondiciones

- El Estudiante aumenta su experiencia total.

---

[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
