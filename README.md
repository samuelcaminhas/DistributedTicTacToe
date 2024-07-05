Opa, bão

Só explicando aqui, nesse código acabei utilizando botões pra atualizar de forma mais simples
no lugar de uma matriz por pura falta de pratica, mas ta funcionando.

Abaixo estão os requisitos q foram solicitados:

Requisitos Funcionais
Criação e Entrada em Partidas
1. Criação de partida:
• O primeiro jogador deve poder criar uma nova partida. Criado, eh a função createNewGame
• Um ID único para a partida deve ser gerado e exibido ao jogador.Gerado na função createNewGame um numero de 100000 ate 999999
• Este ID deve ser utilizado pelo segundo jogador para entrar na partida. O ID ta sendo exibido na primeira tela, logo apos entrar na sala de jogo
2. Entrada em partida:
• O segundo jogador deve poder inserir o ID da partida para participar do jogo.
Funciona
• O aplicativo deve validar a existência do ID da partida antes de permitir a entrada.
Funciona também
Jogabilidade
3. Realização de jogadas:
• Os jogadores devem alternar as jogadas (Jogador 1 com "X" e Jogador 2 com "O").
Ta funcionando
• Uma jogada consiste em selecionar uma posição vazia no tabuleiro. O tabuleiro não precisa ser bonito, mas deve representar minimamente um jogo da velha.
Ta funcionando, como falei, eh por botão, mas ta funcionando
• A posição selecionada deve ser atualizada no Firebase Realtime Database.
atualiza
4. Validação de jogadas:
• O aplicativo deve impedir que uma posição já ocupada receba uma nova jogada.
cada botão tem um listener que verifica se a posição ja foi preenchida
• As jogadas devem ser sincronizadas em tempo real entre os dispositivos dos jogadores.
Ta sincronizando
Determinação de Resultado
5. Verificação de vitória:
• O aplicativo deve verificar se um jogador atingiu uma combinação vencedora (linhas, colunas ou diagonais).
Ta vendo sim, mas eh via validação de array
• Em caso de vitória, o aplicativo deve notificar ambos os jogadores sobre o resultado e encerrar a partida.
Avisa quem ganhou
6. Verificação de empate:
Valida também
• Se todas as posições do tabuleiro estiverem preenchidas sem um vencedor, o aplicativo deve declarar um empate e notificar ambos os jogadores.

7. Notificação de resultado:
• O aplicativo deve exibir mensagens claras para os jogadores informando
sobre vitória, derrota ou empate.

Requisitos não funcionais
Requisitos Técnicos
• Nível de API: o projeto deve ser desenvolvido para o nível de API mínimo 26.
• Arquitetura de software: por se tratar de um projeto pequeno a arquitetura de
software a ser utilizada é o MVC para implementação do aplicativo, garantindo
modularidade e facilidade de manutenção. Evite acessos diretos da View à
comunicação e fique atento com as possíveis exceções que podem ser geradas.
• Boas práticas de programação: utilize boas práticas de programação no
desenvolvimento do código e para os commits do repositório. Fique atento à
proporcionalidade de seus commits e siga as regras de conventional commits
(https://www.conventionalcommits.org/en/v1.0.0/).
• Padrões de projeto: aplique padrões de projeto apropriados, quando possível e
necessário.
