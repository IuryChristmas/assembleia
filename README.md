# Tarefa Bônus 4 - Versionamento da API
## Como você versionaria a API da sua aplicação? Que estratégia usar?

Seguiria o esquema de branchs: Master, Develop, Hotfix, Features e Releases
Existiriam as branchs principais a partir das quais as outras iriam derivar, seriam elas: Master e Develop

Onde a partir da develop seriam criadas sempre que necessárias as branchas de features e releases, seguindo a seguinte nomenclatura, para as branchs do tipo feature que envolveriam qualquer nova funcionalidade para a aplicação deveriam ser feitas seguindo o padrão feature/nomeQueRepresenteFuncionalidade e após concluída a branch deverá ser atualizada com a develop para receber qualquer atualização que possa ter sido feita e após isso, ser aberto o merge request para à develop e após aprovação, ser feito o merge.

O que nos leva as branchs do tipo release, que devem ser feitas a partir da develop, após a conclusão das branchs do tipo feature terem sido mergeadas, features estas que podem ser definidas antes por meio de reuniões, quais irão entrar em uma determinada release para que então possam ser testadas as melhorias feitas e uma vez aprovadas, a branch release criada, deverá ser feito o merge com o master. A criação da branch release deverá seguir o padrão release/2020(substituir por ano atual).0(alterado somente quando for definido que a versão tiver sido concluida e uma nova estiver sendo criada).1(deverá ser adicionado +1 a cada nova branch release criada). Após aprovado o merge, a brancha release deverá ser excluida.

A partir da branch master é que serão criadas as branchs do tipo hotfix, que envolverão qualquer bug descoberto em produção, após a correção do mesmo, a modificação deverá ser mergeada para master e então a develop deverá ser atualizada com as alterações na master, para recuperar a correção realizada. A branch de hotfix deverá seguir o seguinte padrão hotfix/nomeFuncionalidadeCorrigida.

A master é também a branch que deverá ser mais estável, pois serão as funcionalidades, correções presentes nela que serão enviadas para produção.

![git_flow](https://github.com/IuryChristmas/assembleia/blob/master/gitflow.png)
