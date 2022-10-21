import java.util.concurrent.Semaphore;

/**
 * Encapsula dados e comportamento em relacao aos competidores num autodromo
 * garantindo exclusao mutua
 */
public class Autodromo
{
  /**
   * Flag para indicar o fim da corrida
   */
  private boolean fimCorrida;

  /**
   * Posicao do C1 na corrida
   */
  private int posicaoC1;

  /**
   * Posicao da C2 na corrida
   */
  private int posicaoC2;

  /**
   * Semaforo para os movimentos da C1
   */
  Semaphore semaphoreC1;

  /**
   * Semaforo para os movimentos da C2
   */
  Semaphore semaphoreC2;

  /**
   * Construtor padrao da classe Autodromo.
   */
  public Autodromo()
  {
    this.posicaoC1 = 1;
    this.posicaoC2 = 1;
    this.fimCorrida = false;
    
    this.semaphoreC2 = new Semaphore(0);
    this.semaphoreC1 = new Semaphore(1);
    
    System.out.println("BANG !!!!!\nE ELES PARTIRAM !!!!!");
  }
  
  /**
   * Altera a posicao da C1 em relacao a um numero aleatorio
   * 
   * @param aleatorio parametro usado como criterio para gerar velocidade
   *                  ao compeditor C1
   */
  private void alteraPosicaoC1(int aleatorio)
  {
    if((aleatorio >= 0) && (aleatorio <= 4))
    {
      
      this.posicaoC1 += 3;
    }
  }
  /**
   * Altera a posicao da C2 em relacao a um numero aleatorio
   * 
   * @param aleatorio parametro usado como criterio para gerar velocidade
   *                  ao compeditor C2
   */
  
  private void alteraPosicaoC2(int aleatorio)
  {
    if((aleatorio >= 0) && (aleatorio <= 4))
    {
      this.posicaoC2 += 3; 
    }
  }
  
  /**
   * Apresentar a situacao da corrida para o publico
   */
  private void escrever()
  {
    if(this.posicaoC1 == this.posicaoC2)
    {
      for(int i = 1; i <= this.posicaoC1; i++)
      {
        System.out.print("");
      }
      
    }
    else
    {
      int limite = (this.posicaoC1 > this.posicaoC2)
                 ? this.posicaoC1 : this.posicaoC2;
      
      for(int i = 1; i <= limite; i++)
      {
        if(i == this.posicaoC1)
        {
          System.out.print("C1");
        }
        else if(i == this.posicaoC2)
        {
          System.out.print("C2");
        }
        else
        {
          System.out.print("_");
        }
      }
      
      System.out.println();
    }
  }
  
  /**
   * Movimenta C1 fazendo com que avance na corrida. Uma alteracao de
   * movimentacao de C1 ocorre a cada avanco,A escolha entre uma velocidade 
   * ou outra é feita de forma aleatoria.
   * 
   * @param avanco refere-se o quanto o competidor avanca na competicao.
   * @return retorna TRUE se a corrida acabou, ou seja se o competidor
   *         alcancou a posicao maior ou igual a 50.
   */
  
  public boolean movimentaC1(int avanco)
  {
    try
    {
      this.semaphoreC2.acquire();

      // o metodo abaixo pode mudar a posicao da C1
      alteraPosicaoC1(avanco);

      if(!this.fimCorrida)
      {
        escrever();
      }

      this.semaphoreC1.release();
    }
    catch(InterruptedException exception)
    {
      exception.printStackTrace();
    }
    
    if(this.posicaoC1 >= 50)
    {
      this.fimCorrida = true;
    }

    return this.fimCorrida;
  }
  
  
  /**
   * Movimenta a C2 fazendo com que avance na corrida. Uma alteracao de
   * movimentacao de C2 ocorre a cada avanco,A escolha entre uma velocidade 
   * ou outra é feita de forma aleatoria.
   * 
   * @param avanco refere-se o quanto o competidor avanca na competicao.
   * @return retorna TRUE se a corrida acabou, ou seja se o competidor
   *         alcancou a posicao maior ou igual a 50.
   */
  
  public boolean movimentaC2(int avanco)
  {
    try
    {
      this.semaphoreC1.acquire();

      // o metodo abaixo muda a posicao do C2
      alteraPosicaoC2(avanco);

      if(!this.fimCorrida)
      {
        escrever();
      }

      this.semaphoreC2.release();
    }
    catch(InterruptedException exception)
    {
      exception.printStackTrace();
    }
    
    if(this.posicaoC2 >= 50)
    {
      this.fimCorrida = true;
    }

    return this.fimCorrida;
  }
  
  /**
   * Anuncia o vencedor da competicao.
   */
  public void vencedor()
  {
    if(this.posicaoC2 > this.posicaoC1)
    {
      System.out.println("O C2 VENCE. ");
    }
    else if(this.posicaoC2 < this.posicaoC1)
    {
      System.out.println("o C1 VENCE.");
    }
    else
    {
      System.out.println("TEMOS UM EMPATE");
    }
  }
}