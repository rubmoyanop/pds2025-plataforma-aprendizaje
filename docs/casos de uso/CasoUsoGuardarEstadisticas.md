[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
[![Ver todos los CdU](https://img.shields.io/badge/📋_Ver_todos_los_CdU-C62828?style=for-the-badge)](./CasosDeUso.md)

# Caso de Uso Extendido: Guardar estadísticas de uso

## Actores

- **Sistema:** Principal. Almacena la información correspondiente a las estadísticas del Estudiante.
- **Estudiante:** Consulta las estadísticas de los cursos realizados para tener una visión general de su progreso.

## Precondiciones

- El Estudiante debe estar registrado en la aplicación.

## Flujo principal

1. El Estudiante inicia sesión en la aplicación.
2. El Sistema actualiza el contador de minutos del Estudiante.
3. El Estudiante selecciona un curso.
4. El Sistema actualiza las estadísticas de éxito del Estudiante.
5. El Sistema compara las estadísticas actualizadas con las guardadas.
6. El Sistema actualiza los registros de racha correspondientes.

   Se repiten los pasos 3-6 hasta que el Estudiante se desconecte.

7. El Estudiante cierra sesión.
8. El Sistema detiene el contador de minutos de la sesión y actualiza el contador general.

## Flujos alternativos

- **3A El Estudiante no realiza ningún curso:**
  1. El Sistema actualiza únicamente el tiempo en aplicación.

- **3B El Estudiante consulta las estadísticas guardadas:**
  1. El Sistema actualiza las estadísticas generales con los datos de la sesión registrados hasta el momento.
  2. El Sistema muestra las estadísticas al Estudiante.

## Postcondiciones

- El Estudiante puede consultar sus estadísticas de uso de la aplicación.
  

---

[![Volver al inicio](https://img.shields.io/badge/⬅️_Volver_al_inicio-4CAF50?style=for-the-badge)](../../README.md)
