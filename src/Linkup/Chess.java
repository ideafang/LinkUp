package Linkup;

/* ͼƬ��״̬��װ�� */
/* ��MapPanel�е�initBoard()��ʼ�����̷�������
 *  */
public class Chess {
	private int status; //ͼƬ��ѡ�� 1-20�� 0Ϊ������״̬
	 public Chess(int s){
		 this.status = s;
	 }
	 
	 public int getStatus(){
		 return status;
	 }
	 
	 public void setStatus(int s){
		 this.status = s;
	 }
		
}
