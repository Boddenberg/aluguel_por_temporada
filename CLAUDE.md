# CLAUDE.md

<!--
Como usar:
- Copie este arquivo para a raiz de cada repositório (vale no computador e na nuvem).
- Preencha só a seção "Projeto" em cada repo. O resto é igual em todos.
- Se o repo já tiver um CLAUDE.md, mantenha o que existe e cole a partir de "Regras de trabalho".
-->

## Projeto
- **O que é:** <uma frase>
- **Stack:** <ex.: Expo + TypeScript; servidor Node no Railway; Supabase>
- **Rodar testes:** `<comando>`
- **Checar tipos:** `<comando>`
- **Deploy:** <ex.: push na `beta` → ambiente beta; `producao` → produção>
- **Pastas principais:** <ex.: src/combate, src/mundo, src/ui>
- **Cuidados:** <ex.: nunca mexer em X sem pedir>

---

# Regras de trabalho

O objetivo é economizar contexto sem perder qualidade. Contexto pequeno = respostas mais rápidas, mais precisas e mais baratas.

## 1. Início de sessão
- Se a seção "Projeto" deste arquivo ainda tiver campos `<...>`, preencha antes de começar: olhe o repo (package.json, README, estrutura de pastas, workflows) e escreva os campos. Inclua essa mudança no primeiro commit da sessão, sem perguntar.
- Se a tarefa envolver outro repositório, antes de mexer nele leia o `CLAUDE.md` dele (preenchendo a seção "Projeto" dele, se estiver vazia). Se ele não tiver `CLAUDE.md`, crie um com este mesmo arquivo, preencha o "Projeto" e inclua no commit.
- Se existir `HANDOFF.md` na raiz, leia primeiro e continue dele.
- Se existir `TAREFAS.md` com itens pendentes, retome do próximo item.
- Não explore o repositório inteiro. Use a seção "Projeto" acima e abra só os arquivos que a tarefa exige.

## 2. Passagem de bastão (handoff)
- Uma sessão = um assunto. Quando terminar um assunto ou a conversa ficar longa (muitos arquivos lidos, várias imagens, ~1h de trabalho), pare e avise: **"Bom ponto para handoff + /clear."**
- Quando eu disser **"handoff"** (ou rodar `/handoff`), escreva o `HANDOFF.md` com no máximo ~60 linhas:
  1. **Objetivo atual** (1–2 frases)
  2. **Feito** (mudanças concluídas e arquivos tocados)
  3. **Decisões** (o quê e por quê, para não refazer)
  4. **Estado** (funciona / quebrado / já em produção?)
  5. **Próximos passos** (em ordem)
  6. **Armadilhas** (erros já vistos e como resolveu)
  Depois responda só: "HANDOFF.md salvo — pode dar /clear."

## 3. Listas grandes de tarefas (áudios com dezenas de pedidos)
1. Transforme o pedido em um `TAREFAS.md`: uma linha por tarefa, com checkbox, agrupadas por área/arquivo.
2. Junte **todas** as dúvidas em uma única pergunta no início. Não pare no meio para perguntar.
3. Divida em blocos de ~10–15 tarefas da mesma área. Ordene os blocos para evitar retrabalho (ex.: balanço antes do que depende do balanço; divulgação/prints por último).
4. Delegue cada bloco a **um** subagente, **um bloco por vez**. Nunca um subagente por tarefa.
5. O subagente recebe só o bloco, os arquivos da área e o `HANDOFF.md`. Ele implementa, roda testes e tipos uma vez, faz um commit e devolve um resumo curto (feito, arquivos, pendências). Sem despejar arquivos ou logs.
6. A sessão principal só coordena: marca o `TAREFAS.md`, atualiza o `HANDOFF.md` e manda o próximo bloco. Assim ela não cresce.
7. Deploy uma vez por bloco ou no fim, nunca a cada tarefa.

## 4. Subagentes e paralelismo
- Fora de listas grandes, prefira fazer o trabalho na própria sessão.
- No máximo **2 frentes em paralelo**, e só se mexerem em áreas que não se tocam.
- Se eu pedir "duas opções pra escolher", entregue primeiro os dois planos em texto e implemente só a escolhida.

## 5. Nuvem e orquestração
- Nunca monte um orquestrador com várias sessões na nuvem.
- Nunca crie lembretes recorrentes para "acordar e checar frentes".
- Para acompanhar deploy ou CI: um único lembrete curto que só confere o status e me avisa.

## 6. Mídia e saídas grandes
- Vídeo: use 3–4 frames-chave, nunca o vídeo inteiro.
- Sprites e GIFs: escreva um script que gere os arquivos; olhe só o resultado final, não cada frame.
- Testar interface: prefira ler texto/DOM da página a tirar screenshot a cada passo. Prints só no QA final.
- Logs, listas e saídas de teste: filtre com `grep`, `tail`, `head`. Nunca despeje tudo no contexto.
