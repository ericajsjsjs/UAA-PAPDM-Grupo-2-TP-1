package com.registroauto.km0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import android.app.Activity
import androidx.compose.ui.viewinterop.AndroidView
import android.widget.NumberPicker
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.registroauto.km0.ui.theme.KM0
import java.text.DecimalFormat

data class Auto(
    val marca: String,
    val modelo: String,
    val ano: Int,
    val precio: String,
    val kilometraje: String,
    val imagenUrl: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KM0 {
                AutoRegistrationApp()
            }
        }
    }
}

@Composable
fun AutoRegistrationApp() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RegisterScreen()
        }
    }
}

@Composable
fun RegisterScreen() {

    var marca by remember { mutableStateOf(TextFieldValue("")) }
    var modelo by remember { mutableStateOf(TextFieldValue("")) }
    var ano by remember { mutableIntStateOf(2024) }
    var precio by remember { mutableStateOf(TextFieldValue("")) }
    var kilometraje by remember { mutableStateOf(TextFieldValue("")) }
    var imagenUrl by remember { mutableStateOf(TextFieldValue("")) }


    var showError by remember { mutableStateOf(false) }


    var autos by remember { mutableStateOf(listOf<Auto>()) }


    val context = LocalContext.current
    val activity = context as? Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        TextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text("Marca") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && marca.text.isBlank() // Borde rojo si hay error
        )
        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            value = modelo,
            onValueChange = { modelo = it },
            label = { Text("Modelo") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && modelo.text.isBlank() // Borde rojo si hay error
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Año")
                AndroidView(
                    modifier = Modifier.wrapContentSize(),
                    factory = { context ->
                        NumberPicker(context).apply {
                            minValue = 1700
                            maxValue = 2025
                            value = ano
                            setOnValueChangedListener { _, _, newVal ->
                                ano = newVal
                            }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.width(8.dp))


            Column(modifier = Modifier.weight(1f)) {
                TextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") },
                    isError = showError && precio.text.isBlank() // Borde rojo si hay error
                )
            }

            Spacer(modifier = Modifier.width(8.dp))


            Column(modifier = Modifier.weight(1f)) {
                TextField(
                    value = kilometraje,
                    onValueChange = { kilometraje = it },
                    label = { Text("Kilometraje") },
                    isError = showError && kilometraje.text.isBlank()
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            value = imagenUrl,
            onValueChange = { imagenUrl = it },
            label = { Text("Imagen URL") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && imagenUrl.text.isBlank()
        )
        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {

                val precioValido = precio.text.toDoubleOrNull() != null
                val kilometrajeValido = kilometraje.text.toIntOrNull() != null

                if (!precioValido || !kilometrajeValido) {
                    showError = true
                    Toast.makeText(context, "El precio y el kilometraje deben ser valores numéricos", Toast.LENGTH_SHORT).show()
                } else if (marca.text.isBlank() || modelo.text.isBlank() || precio.text.isBlank() ||
                    kilometraje.text.isBlank() || imagenUrl.text.isBlank()
                ) {
                    showError = true
                    Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                } else {

                    val nuevoAuto = Auto(
                        marca = marca.text,
                        modelo = modelo.text,
                        ano = ano,
                        precio = precio.text,
                        kilometraje = kilometraje.text,
                        imagenUrl = imagenUrl.text
                    )
                    autos = autos + nuevoAuto


                    marca = TextFieldValue("")
                    modelo = TextFieldValue("")
                    precio = TextFieldValue("")
                    kilometraje = TextFieldValue("")
                    imagenUrl = TextFieldValue("")


                    showError = false


                    Toast.makeText(context, "Auto registrado", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar Auto")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                Toast.makeText(context, "Saliendo de la aplicación", Toast.LENGTH_SHORT).show()
                activity?.finish()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salir")
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(autos) { auto ->
                AutoItem(auto = auto, onDelete = {
                    autos = autos - auto
                })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun AutoItem(auto: Auto, onDelete: () -> Unit) {

    val numberFormat = DecimalFormat("#,###")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "${auto.marca} ${auto.modelo}",
            style = MaterialTheme.typography.titleLarge,
        )


        LazyRow(
            modifier = Modifier.fillMaxWidth(),
        ) {

            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Foto", fontSize = 25.sp)
                    AsyncImage(
                        model = auto.imagenUrl,
                        contentDescription = "Imagen de ${auto.marca} ${auto.modelo}",
                        modifier = Modifier.size(200.dp) // Tamaño de la imagen
                    )
                }
            }


            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Año", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(text = "${auto.ano}", fontSize = 30.sp)
                }
            }


            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Precio", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(text = "${numberFormat.format(auto.precio.toDouble())}$", fontSize = 30.sp)
                }
            }


            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Kilometraje", fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(text = "${numberFormat.format(auto.kilometraje.toInt())} km", fontSize = 30.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))


        Button(
            onClick = onDelete,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Eliminar Auto")
        }
    }
}