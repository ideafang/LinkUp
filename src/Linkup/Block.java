package Linkup;

/* ͼƬ��״̬��װ�� */
/* ��Map�е�initBoard()��ʼ�����̷�������
 *  */
public class Block {
	private int status; //ͼƬ��ѡ�� 1-20�� 0Ϊ������״̬
	 public Block(int s){
		 this.status = s;
	 }
	 
	 public int getStatus(){
		 return status;
	 }
	 
	 public void setStatus(int s){
		 this.status = s;
	 }
		
}
