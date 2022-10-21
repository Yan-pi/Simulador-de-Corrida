/**
 * Carro 2
 */
public class C2 extends Thread
{
  /**
   * Instancia do autodromo
   */
  private Autodromo autodromo;
  
  /**
   * Flag para indicar o fim da corrida 
   */
  private boolean fim;

  /**
   * Construtor padrao da classe C2
   * 
   * @param autodromo parametro de controle do competidor C2 numa corrida
   */
  public C2 (Autodromo autodromo)
  {
    this.fim = false;
    this.autodromo = autodromo;
  }

  /**
   * Metodo especializado da classe Java Thread que define o comportamento
   * do competidor C2
   */
  public void run()
  {
    int avanca = 0;
    
    while(!this.fim)
    {
      avanca = (int)(Math.random() * 10);
      
      this.fim = this.autodromo.movimentaC2(avanca);
    }
  }
}