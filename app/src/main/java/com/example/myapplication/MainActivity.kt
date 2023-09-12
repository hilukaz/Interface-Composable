package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import android.app.DatePickerDialog;
import android.widget.DatePicker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    App()
                }
            }
        }
    }
}

@Composable
fun App(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            SimpleCenterAlignedTopAppBar()
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){

            Formulario()


        }
        /*Column(
            horizontalAlignment= Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(100.dp),
        ) {
            ExtendedFloatingActionButtonTextSample()
        }*/
    }
}




@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}





@Preview
@Composable()
fun previewForm(){
    Formulario()
}


@Composable
fun ExtendedFloatingActionButtonTextSample(telefone: String, endereco: String,nome:String, observacao:String,origem:String) {
    ExtendedFloatingActionButton(onClick = {

        val agenda = hashMapOf(//mapeia todas as variáveis atribuindo a ela um valor
            "telefone" to telefone,
            "endereco" to endereco,
            "nome" to nome,
            "observacao" to observacao,
            "origem" to origem
        )
//        FirebaseFirestore.getInstance().collection("agendamentos")
//            .add(agenda)//instancia o firebase, acessa a collection agendamentos e adiciona o hashMapOf

        // Limpar os campos após o envio
        /*dataContato.value = Calendar.getInstance().time
        endereco.value = ""
        nome.value = ""
        observacao.value = ""
        origem.value = ""*/
    }) {
        Text(text = "Cadastrar usuário")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario() {
    val telefone = remember { mutableStateOf("") }
    val endereco = remember { mutableStateOf("") }
    val nome = remember { mutableStateOf("") }
    val observacao = remember { mutableStateOf("") }
    val origem = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            "Cadastro",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 40.sp),
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(60.dp))//espaço entre o texto Cadastro e forms

        TextField(
            value = telefone.value,
            onValueChange = { telefone.value = it },
            label = { Text("Telefone") },
            modifier = Modifier.testTag("telefoneTextField")
        )
        TextField(
            value = nome.value,
            onValueChange = { nome.value = it },
            label = { Text("Nome") },
            modifier = Modifier.testTag("nomeTextField")
        )

        val context = LocalContext.current
        val calendar = Calendar.getInstance()
        var selectedDateText by remember { mutableStateOf("") }
// Fetching current year, month and day
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
        val datePicker = DatePickerDialog(
            context,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                selectedDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            }, year, month, dayOfMonth
        )
        datePicker.datePicker.minDate = calendar.timeInMillis

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    datePicker.show()
                },
                modifier = Modifier
                    .width(300.dp)
                    .testTag("dateTextField")

            ) {
                Text(
                    text = if (selectedDateText.isNotEmpty()) {
                        "Data Selecionada $selectedDateText"
                    } else {
                        "Data de Contato"
                    }
                )
            }
        }

        TextField(
            value = observacao.value,
            onValueChange = { observacao.value = it },
            label = { Text("Observacao") },
            modifier = Modifier.testTag("observacaoTextField")
        )
        ExposedDropdownMenuSample()
        Spacer(modifier = Modifier.height(40.dp))
        ExtendedFloatingActionButtonTextSample(telefone.value,nome.value,endereco.value,observacao.value,origem.value)
    }
}

@Preview
@Composable
fun DatePreview(){
    dateee()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleCenterAlignedTopAppBar() {
    val customColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        Color.Gray,
    )
    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                colors = customColors,
                title = {
                    Text(
                        "Cadastro Firestore",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.AccountBox,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

            }
        }
    )
}
@Preview
@Composable()
fun Drop(){
    ExposedDropdownMenuSample()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuSample() {
    val options = listOf("Facebook", "Telefone", "E-mail", "Whatsapp", "Instagram")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.testTag("observacaoTextField"),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Origem") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable
fun dateee(){
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var selectedDateText by remember { mutableStateOf("") }
// Fetching current year, month and day
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, year, month, dayOfMonth
    )
    datePicker.datePicker.minDate = calendar.timeInMillis

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                datePicker.show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = if (selectedDateText.isNotEmpty()) {
                    "Data Selecionada $selectedDateText"
                } else {
                    "Data de Contato"
                }
            )
        }
    }
}