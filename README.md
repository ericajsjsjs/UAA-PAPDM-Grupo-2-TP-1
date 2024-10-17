# UAA-PAPDM-Grupo-2-TP-1
TP Primer parcial
Grupo 2: INTEGRANTES
         -  Nahuel Matías Domínguez Mayyeregger (@Nahueld002)
         - Erica Noemi Sanabria Palavecino (@ericajsjsjs)
         - Guido Alejandro Nuñez Vera (@guidonz)

# Proyecto Android: Registro de Autos
Este proyecto de Android Studio es una aplicación que permite registrar, visualizar y gestionar autos en venta. Utiliza Jetpack Compose para la interfaz gráfica, integrando Material Design y componentes como formularios y listas interactivas. Además, implementa la gestión básica de formularios, validaciones y almacenamiento temporal de autos registrados en una lista dentro del estado de la aplicación.

Estructura del Proyecto
- MainActivity
MainActivity es el punto de entrada de la aplicación. Extiende ComponentActivity, y en el método onCreate, establece el contenido de la interfaz mediante el uso de Jetpack Compose. Se llama a la función composable AutoRegistrationApp, que construye la estructura de la aplicación.

KM0: Se utiliza para aplicar el tema de la aplicación (definido en el archivo de temas del proyecto).
AutoRegistrationApp: Función principal que contiene la pantalla donde los usuarios pueden registrar autos.
- AutoRegistrationApp
Esta función composable establece el tema global de la aplicación. Utiliza MaterialTheme para aplicar estilos y colores predefinidos. Dentro de esta función, se llama a RegisterScreen que es la pantalla principal de registro.

- RegisterScreen
RegisterScreen es la pantalla donde se gestiona todo el formulario de registro de autos. Esta pantalla incluye:

Formulario de entrada de datos: Incluye campos para ingresar la marca, modelo, año, precio, kilometraje e imagen URL del auto.
Validación de campos: El botón "Registrar Auto" realiza una validación básica de los campos, mostrando mensajes de error con Toast si algún campo obligatorio está vacío o si los campos de precio y kilometraje no tienen valores numéricos.
Lista de autos registrados: Una lista de autos registrados se muestra debajo del formulario utilizando un LazyColumn. Los autos se muestran en tarjetas que incluyen detalles como imagen, precio, año, y kilometraje.
2.4 AutoItem
AutoItem es la función composable que representa cada auto registrado en la lista. Los detalles del auto, como la imagen, precio, año y kilometraje, se organizan en una fila horizontal (LazyRow). Los detalles incluyen:

Imagen del auto: Cargada de forma asíncrona usando coil.compose.AsyncImage.
Año, precio y kilometraje: Mostrados de forma destacada.
Botón "Eliminar Auto": Permite al usuario eliminar el auto de la lista.


Funciones y Componentes Principales
Formulario de registro: Compuesto por varios campos de texto para ingresar datos del auto y un selector numérico (NumberPicker) para seleccionar el año.
Validaciones: Se asegura que los campos no estén vacíos y que los valores de precio y kilometraje sean numéricos.
Interactividad: Los datos ingresados por el usuario se almacenan en un estado administrado con remember y mutableStateOf.
Lista dinámica: La lista de autos se actualiza en tiempo real al agregar o eliminar autos, usando LazyColumn para mostrar los elementos.


Diseño y Temas
El proyecto aplica Material Design 3 para asegurar una apariencia moderna y cohesiva. El tema de la aplicación se gestiona a través de la función KM0 que está definida en el archivo theme del proyecto, aplicando colores, tipografía y otros estilos visuales.


Archivos Importantes
MainActivity.kt: Contiene la actividad principal y el punto de entrada de la aplicación.
theme: Define los estilos visuales globales de la aplicación.
AutoItem.kt: Define cómo se muestran los autos registrados en la interfaz.

# Resumen:
Este proyecto es una aplicación sencilla y eficaz para gestionar el registro de autos. Con una interfaz amigable y validaciones básicas, ofrece una base sólida para ser extendida a aplicaciones más complejas con persistencia de datos y funcionalidades avanzadas.
