<?php
    use \Psr\Http\Message\ServerRequestInterface as Request;
    use \Psr\Http\Message\ResponseInterface as Response;

    require '../vendor/autoload.php';

    $config['displayErrorDetails'] = true;
    $config['addContentLengthHeader'] = false;

    $config['db']['host'] = "localhost";
    $config['db']['user'] = "root";
    $config['db']['pass'] = "PwUserMysql1234";
    $config['db']['dbname'] = "INATEL_T141_DM107_Final_Homework";

    //$app = new \Slim\App;
    $app = new \Slim\App(["config" => $config]);
    $container = $app->getContainer();

    $container['db'] = function ($c) {
        $dbConfig = $c['config']['db'];
        $pdo = new PDO("mysql:host=" . $dbConfig['host'] . ";dbname=" . $dbConfig['dbname'], $dbConfig['user'], $dbConfig['pass']);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
        $db = new NotORM($pdo); return $db;
    };
/*

    $app->get('/produtos/{id}', function(Request $request, Response $response)
    {
        //Retorna o produto identificado pelo id
    }

    $app->post('/produtos', function(Request $request, Response $response)
    {
        //Cria um novo produto
    }

    $app->put('/produtos/{id}', function(Request $request, Response $response)
    {
        //Atualiza o produto identificado pelo id
    }

    $app->delete('/produtos/{id}', function(Request $request, Response $response)
    {
        //Remove o recurso identificado pelo id
    }

*/

    $app->get('/api/entrega', function (Request $request, Response $response) {
        $entregas = $this->db->entregas();
        return $response->withJson($entregas);
    });


    $app->get('/api/entrega/{numeroDoPedido}', function (Request $request, Response $response)
    {
        $numeroDoPedido = $request->getAttribute("numeroDoPedido");
        $entrega = $this->db->entregas()->where('numeroDoPedido', $numeroDoPedido);
        if ($entrega->fetch()) {
            return $response->withJson($entrega);
        }else{
            return $response->withStatus(404);
        }

    });

    $app->post('/api/entrega/create', function (Request $request, Response $response)
    {
        $body = $request->getBody();
        $convert_to_array = explode('&', $body);
        for($i=0; $i < count($convert_to_array ); $i++){
            $key_value = explode('=', $convert_to_array [$i]);
            $end_array[$key_value [0]] = $key_value [1];
        }

        $numeroDoPedido = $end_array["numeroDoPedido"];
        $idDoCliente = $end_array["idDoCliente"];

        $entrega = $this->db->entregas()->insert(
            array(
                "numeroDoPedido" => $numeroDoPedido,
                "idDoCliente" => $idDoCliente
            )
        );

        return $response->withJson($entrega);

    });

    $app->delete('/api/entrega/{numeroDoPedido}', function(Request $request, Response $response)
    {
        $numeroDoPedido = $request->getAttribute("numeroDoPedido");
        $entrega = $this->db->entregas()->where('numeroDoPedido', $numeroDoPedido);
        if ($entrega->fetch()) {
            $deleted = $entrega->delete();
            return $response->withStatus(200);
        }else{
            return $response->withStatus(404);
        }
    });

    $app->put('/api/entrega/update/{numeroDoPedido}', function (Request $request, Response $response)
    {
        $numeroDoPedido = $request->getAttribute("numeroDoPedido");
        $body = $request->getBody();
        $convert_to_array = explode('&', $body);
        for($i=0; $i < count($convert_to_array ); $i++){
            $key_value = explode('=', $convert_to_array [$i]);
            $end_array[$key_value [0]] = $key_value [1];
        }

        $nomeDoRecebedor = $end_array["nomeDoRecebedor"];
        $cpfDoRecebedor = $end_array["cpfDoRecebedor"];
        $diaDaEntrega = $end_array["diaDaEntrega"];
        $mesDaEntrega = $end_array["mesDaEntrega"];
        $anoDaEntrega = $end_array["anoDaEntrega"];
        $horaDaEntrega = $end_array["horaDaEntrega"];
        $minutoDaEntrega = $end_array["minutoDaEntrega"];

        $to = $anoDaEntrega . "-" . $mesDaEntrega . "-" . $diaDaEntrega . " " . $horaDaEntrega . ":" . $minutoDaEntrega . ":00";
        $timestamp = strtotime($to); //convert to Unix timestamp
        $dataHoraDaEntrega = date("Y-m-d H:i:s",$timestamp);
        //$response->getBody()->write("numeroDoPedido = $numeroDoPedido, nomeDoRecebedor = $nomeDoRecebedor, cpfDoRecebedor = $cpfDoRecebedor , dataHoraDaEntrega = $dataHoraDaEntrega");

        $entrega = $this->db->entregas()->where('numeroDoPedido', $numeroDoPedido);
        $updated = null;
        if ($entrega->fetch()) {
           $entregaUpdate=(array(
                "nomeDoRecebedor" => $nomeDoRecebedor,
                "cpfDoRecebedor" => $cpfDoRecebedor,
                "dataHoraDaEntrega" => $dataHoraDaEntrega
                )
            ); //um array com os dados da tarefa
           $updated = $entrega->update($entregaUpdate);
           $entrega = $this->db->entregas()->where('numeroDoPedido', $numeroDoPedido);
           return $response->withJson($entrega);
        }else{
            return $response->withStatus(404);
        }

    });

    $app->run();

?>