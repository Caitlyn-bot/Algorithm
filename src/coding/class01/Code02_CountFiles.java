package coding.class01;

import java.io.File;
import java.util.Stack;

/**
 * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算
 * @author 张志伟
 * @version v1.0
 */
public class Code02_CountFiles {
    /**
     * 非递归，使用栈进行DFS,如果使用队列就是BFS
     * @param folderPath
     * @return
     */
    public static int getFileNumber1(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()){
            //如果既不是文件夹也不是文件，返回0
            return 0;
        }
        if (root.isFile()){
            //如果是文件，返回1
            return 1;
        }
        //root是文件夹
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files = 0;
        while (!stack.isEmpty()){
            File folder = stack.pop();
            for(File next : folder.listFiles()){
                if (next.isFile()){
                    //是文件
                    files++;
                }
                if (next.isDirectory()){
                    //是文件夹
                    stack.push(next);
                }
            }
        }
        return files;
    }

    public static int getFileNumber2(String folderPath){
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()){
            //如果既不是文件夹也不是文件，返回0
            return 0;
        }
        if (root.isFile()){
            //如果是文件，返回1
            return 1;
        }
        //root是文件夹
        return process1(root);
    }

    private static int process1(File root) {
        if (root.isFile()){
            return 1;
        }
        int res = 0;
        if (root.isDirectory()){
            for(File next : root.listFiles()){
                res += process1(next);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String path = "F:\\A_春招";
        System.out.println(getFileNumber1(path));
        System.out.println(getFileNumber2(path));
    }
}
