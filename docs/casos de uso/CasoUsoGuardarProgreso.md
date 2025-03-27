### Caso de Uso Extendido: Guardar progreso del curso

**Actores**

*   **Estudiante**: Quiere guardar su estado actual en un curso para poder continuar más tarde desde el mismo punto.
*   **Sistema**: Almacena el estado del curso del Estudiante de forma persistente.

**Precondiciones**

*   El Estudiante debe estar autenticado en el sistema.
*   El Estudiante debe haber iniciado un curso y estar visualizando o respondiendo un ejercicio.

**Flujo principal**

1.  El Estudiante decide guardar su progreso actual e invoca la acción correspondiente (ej., a través de un botón "Guardar y Salir", un guardado automático al cerrar, o una opción de menú).
2.  El Sistema identifica el curso actual que el Estudiante está realizando.
3.  El Sistema identifica la posición actual del Estudiante dentro del curso (ej., el índice del próximo ejercicio/tarjeta a mostrar según la estrategia de aprendizaje seleccionada).
4.  El Sistema identifica la estrategia de aprendizaje seleccionada por el Estudiante para este curso.
5.  El Sistema captura cualquier estado relevante de la estrategia de aprendizaje (ej., contadores para repetición espaciada, historial reciente de preguntas en modo aleatorio).
6.  El Sistema almacena de forma persistente toda la información recopilada (identificador del curso, posición, estrategia, estado de la estrategia) asociada a la cuenta del Estudiante.
7.  El Sistema confirma al Estudiante (si la acción fue manual) que el progreso ha sido guardado exitosamente.
8.  El Estudiante puede cerrar la sesión del curso o la aplicación.

**Flujos alternativos**

*   **2A: Guardado automático:**
    *   1. El Estudiante cierra la ventana del curso o la aplicación sin invocar manualmente la acción de guardar.
    *   2. El Sistema detecta la acción de cierre e inicia automáticamente los pasos 3-7 del flujo principal.
    *   3. El Sistema *no* necesariamente muestra una confirmación explícita (paso 8), pero el guardado se realiza.
    *   4. El caso de uso finaliza.

*   **7A: Error al guardar el progreso:**
    *   1. En el paso 7 del flujo principal, el Sistema encuentra un error al intentar almacenar la información (ej., problema de conexión con la base de datos, error de escritura).
    *   2. El Sistema *no* puede guardar el progreso.
    *   3. El Sistema informa al Estudiante que ha ocurrido un error y el progreso no ha podido ser guardado.
    *   4. El Sistema puede sugerir reintentar la operación.
    *   5. El Estudiante permanece en el estado actual dentro del curso.
    *   6. El caso de uso finaliza (sin éxito en el guardado).

**Postcondiciones**

*   **(Éxito - Flujo Principal o 2A)**: El estado actual del progreso del Estudiante en el curso (posición, estrategia seleccionada y su estado) está almacenado de forma persistente en el Sistema, asociado a su cuenta.
*   **(Éxito - Flujo Principal o 2A)**: El Estudiante podrá reanudar el curso desde este punto guardado en una sesión futura.
*   **(Fallo - Flujo 7A)**: El progreso del Estudiante no se ha guardado. El estado persistente del progreso para ese curso (si existía uno anterior) no ha sido modificado.