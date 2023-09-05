package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

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
fun ExtendedFloatingActionButtonTextSample(dataContato: String, endereco: String,nome:String, observacao:String,origem:String) {
    ExtendedFloatingActionButton(onClick = {

        val agenda = hashMapOf(//mapeia todas as variáveis atribuindo a ela um valor
            "data_contato" to dataContato,
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

@Composable
fun Formulario() {
    val dataContato = remember { mutableStateOf("") }
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
            value = dataContato.value,
            onValueChange = { dataContato.value = it },
            label = { Text("Data contato") },
            modifier = Modifier.testTag("dataTextField")
        )
        TextField(
            value = nome.value,
            onValueChange = { nome.value = it },
            label = { Text("Nome") },
            modifier = Modifier.testTag("nomeTextField")
        )
        TextField(
            value = endereco.value,
            onValueChange = { endereco.value = it },
            label = { Text("Endereço") },
            modifier = Modifier.testTag("enderecoTextField")
        )
        TextField(
            value = observacao.value,
            onValueChange = { observacao.value = it },
            label = { Text("Email") },
            modifier = Modifier.testTag("observacaoTextField")
        )
        TextField(
            value = origem.value,
            onValueChange = { origem.value = it },
            label = { Text("Senha") },
            modifier = Modifier.testTag("origemTextField")
        )
        Spacer(modifier = Modifier.height(40.dp))
        ExtendedFloatingActionButtonTextSample(dataContato.value,nome.value,endereco.value,observacao.value,origem.value)
    }
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
                        "Formulario",
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
