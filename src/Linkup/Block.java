package Linkup;

/* 图片的状态封装类 */
/* 和Map中的initBoard()初始化棋盘方法关联
 *  */
public class Block {
	private int status; //图片的选择 1-20， 0为已消除状态
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
