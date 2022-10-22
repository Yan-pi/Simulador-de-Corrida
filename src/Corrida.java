/**@author Yan fernades, Kailan de souza, Guilherme andrade
*/

/**
 * Encapsula dados e comportamente relacionado a uma corrida entre dois
 * competidores
 */
public class Corrida
{
  /**
   * Metodo principal da linguagem de programacao Java
   *
   * @param args argumentos da linha de comando (nao utilizado)
   */
  public static void main(String[] args)
  {
    Autodromo autodromo = new Autodromo();
    C1 C1 = new C1 (autodromo);
    C2 C2 = new C2 (autodromo);
    
    C1.start();
    C2.start();
    
    try
    {
      C2.join();
      C1.join();
    }
    catch(InterruptedException exception)
    {
      exception.printStackTrace();
    }
    
    autodromo.vencedor();
  }
}