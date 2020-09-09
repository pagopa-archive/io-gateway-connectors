<?php
function main(array $args) : array {

    require 'config.php';

    if (! array_key_exists("dbname", $args) || ! array_key_exists("dbuser", $args) || ! array_key_exists("dbpass", $args)) {
        $json_result = json_decode($emptyForm);
        print_r($json_result);
        return array("body" => $json_result);
    }

    // Default values for dbhost e dbport
    if (! array_key_exists("dbhost", $args)) {
        $args['dbhost'] = "localhost";
    } 
    if (! array_key_exists("dbport", $args)) {
        $args['dbport'] = "5432";
    }

    $dsn = sprintf("pgsql:host=%s port=%d dbname=%s user=%s password=%s",
                      $args['dbhost'],
                      $args['dbport'],
                      $args['dbname'],
                      $args['dbuser'],
                      $args['dbpass']);
  
    try {
        $connection = new PDO($dsn);
        $sth = $connection->prepare("SELECT 0 AS amount,
                                        scadenza AS due_date,
                                        destinatario AS fiscal_code,
                                        false AS invalid_after_due_date,
                                        testo AS markdown,
                                        1 AS notice_number,
                                        titolo AS subject FROM messages");
        $sth->execute();
        
    } catch (PDOException $e) {
            print($e->getMessage() . "\n");
            $result['data'] = $e->getMessage();
            $json_result = json_encode($result);
            return array("body" => $json_result);
    }
      
    //Fetch all rows in the result set
    $result['data'] = $sth->fetchAll(PDO::FETCH_ASSOC);

    //JSON encode
    $json_result = json_encode($result);
    print_r($json_result);

    return array(
    "body" => $json_result
    );
}
?>

