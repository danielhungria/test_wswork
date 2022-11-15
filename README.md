<h1>App WS Work</h1>

<h3>ScreenShot</h3>

<img src="https://i.imgur.com/I0VPyQy.png" height="400 "><img src="https://i.imgur.com/W7Jvi9t.png" height="400"><img src="https://i.imgur.com/XNG0fQm.png" height="400"><img src="https://i.imgur.com/PCS6vBz.png" height="400">

<p>Frameworks e bibliotecas utilizados
    
- RecyclerView
Foi utilizado esse framework devido a facilidade na exibição de grandes quantidades de dados, além de poupar recursos ao reexibir os as views quando faz a rolagem de tela para baixo.

- Retrofit
Biblioteca para facilitar o consumo de APIs, junto com ela, o Gson, para transformar a resposta da API em um objeto.

- Coroutines
Foi utilizado pois ajuda a gerenciar tarefas que demoram para ser executadas, podendo bloquear a main thread e fazer com que o app pare de responder.

- SwipeRefresh 
Utilizado para melhorar a experiência do usuário, pois o usuário não precisa reiniciar o aplicativo para atualizar a lista de carros. Serve também caso o usuário esteja sem internet no momento em que for abrir o app, pois os dados da api não irão chegar. Quando a conexão for restabelecida, ativa o Refresh e atualiza.

- Room
Utilizado para armazenar dados em cache e fazer toda a lógica de cadastro do usuário, escolha do carro, etc.

- Worker
Foi utilizado para realizar trabalhos persistentes em segundo plano, nesse caso, o envio dos dados para api, de tempos em tempos. 


</p>
