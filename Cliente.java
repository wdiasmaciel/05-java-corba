// Arquivo Cliente.java

import CumprimentarApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class Cliente {
  public static void main(String args[]) {
    try {
      /*
       * Cria e inicializa o ORB. O segundo argumento é um objeto Properties 
       * com configurações adicionais do ORB. O null significa que nenhuma 
       * propriedade extra foi informada. O ORB usará apenas os argumentos 
       * da linha de comando (args: host e port).
       */
      ORB orb = ORB.init(args, null);

      // Gera o root naming context:
      org.omg.CORBA.Object objeto = orb.resolve_initial_references("ServidorDeNome");
      NamingContext contextoNome = NamingContextHelper.narrow(objeto);

      /*
       * Resolve o Object Reference in Naming. O segundo parâmetro é a
       * categoria ou tipo (kind) do nome. A string vazia significa que
       * nenhum tipo foi especificado.
       */
      NameComponent componenteNome = new NameComponent("Cumprimentador", "");
      NameComponent nome[] = { componenteNome };
      Cumprimentador cumprimentador = CumprimentadorHelper.narrow(contextoNome.resolve(nome));

      // Chama o objeto servidor Cumprimentador e imprime os resultados:
      String saudacao = cumprimentador.cumprimentar();
      System.out.println(saudacao);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}