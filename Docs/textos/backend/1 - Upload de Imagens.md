Documentação referente a issue 1 do projeto s1mulado, a qual visa implementar da maneira mais eficiente uma rota para o upload de imagens.

Segundo o que encontramos as ideia encontradas nesse [link](https://stackoverflow.com/questions/33279153/rest-api-file-ie-images-processing-best-practices)

## Rota única

A melhor prática para fazer o upload de imagens em uma aplicação backend, em um servidor HTTP, seria através uma rota única para fazer o upload desses arquivos.

A funcionalidade de uma rota única da muito poder para o tratamento dessas imagens e seus dados e posteriormente retorna a URL.

`/images`

## Flow

A partir do momento em que o usuário seleciona uma imagem, seu upload é iniciado automaticamente por meio de uma requisição `POST`, ocorrendo em segundo plano. Esse processo retorna uma URL para acesso à imagem. Essa abordagem é mais eficiente, pois, na maioria das vezes, as imagens não são enviadas isoladamente, mas acompanhadas por outros campos. Além disso, elimina o desconforto do usuário em esperar o tempo de upload manualmente, tornando a experiência mais fluida e agradável.


## Compactação dos Dados

A compactação dos dados é algo essencial para as aplicações web, fornecer imagens em altas resoluções é sinônimo de exigir uma alta banda-larga dos seus usuários. Aqueles que tiverem em condições computacionais precárias terão sua experiência na aplicação muito afetados pelos longos tempos de carregamento.

Conforme é abordado nesse [blog](https://www.baeldung.com/java-resize-image) o Java apresenta algumas maneiras eficientes para fazer a compactação das imagens.



## Uploading Images

Muitas vezes não conseguimos garantir que os serviços de hospedagem de imagens da nossa aplicação vão sempre utilizar o mesmo sistema gerenciador de arquivos. Além de que as grandes empresas como o Google, Amazon tem alta disponibilidade e redundância para o armazenamento das informações .

Logo o mais correto seria utilizar de um bucket como os fornecidos pelo Google Cloud e Amazon S3


[Google cloud](https://www.google.com/search?client=safari&rls=en&q=google+cloud+image+upload+java&ie=UTF-8&oe=UTF-8#fpstate=ive&vhid=zephyr:0&vld=cid:6ce9d3b6,vid:FXiV4WPQveY,st:0&vssid=atritem-https://stackoverflow.com/questions/51358617/upload-a-file-to-google-cloud-storage-java)
[AWS s3](https://www.youtube.com/watch?v=2888bZsFJqY)