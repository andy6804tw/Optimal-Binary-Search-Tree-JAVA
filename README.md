# Optimal binary search tree
## Programming Assignment #2
依照PPT上的演算法，將Optimal binary search tree以Java實作出來。

Input: 
numbers from keyboard ; (You may sort them into an ordered list)

Output: 
1.	You need to output the A array that is used to store the minimum number of comparisons. 
2.	You are also required to output the optimal binary search tree on the screen.

請繳交source code 和 一份說明文件，內需含執行畫面。
無說明文件，以零分計算此作業。

### 說明
最佳化二元搜尋樹(Optimal BST)是在已知的一串序列中得到最短搜尋時間的一棵二元搜尋樹，使用動態規劃方法實作，以下分別介紹如何利用動態規劃計算二維陣列A(最佳平均時間)以及二維陣列R(二元樹根節點)，最後再將陣列R使用自定義Node建立一棵的樹並將它印出來。

### main() 函式-計算最佳二元搜尋樹
一開始要求使用者輸入每一個鍵(key)和機率(probability)兩者以空白隔開，若要結束則輸入 0。建立兩個 ArrayList 分別為 `key` 與 `value` 來儲存鍵值和興對應的機率。key和value都建立儲存完後再以key來做排序，這邊排序是以合併排序實作並採用 Recursive (遞迴)的方法。建立變數n來儲存被搜尋的機率的長度，接著分別建立大小為`[n+2][n+1]`的A和R的二維陣列，以及一個最小值min並給予一個最大值做初始值作為後面演算法比較大小的變數。

第19-25行為初始化整個二維陣列A與R，分別處理對角線初始值給予0以及`A[i][i]` 依序放入每個自己本身被搜尋的機率，24和25行是將陣列最右下角給予0(在JAVA中宣告陣列時即初始化0了，固可省略此兩行，因考慮其他程式語言列如C，故還是將20、24、25初始化為0寫上)。

第27~43行是使用動態規劃方法求出 Optimal BST 的演算法， `diagonal` 是控制斜對角線處理的位置從1開始，例如n=4時要先處理 `[1][2]`、`[2][3]`、`[3][4]` 即28行迴圈所做的事，第30行變數min為初始化變數要計算每一次中的最小值。32-34行即為算出 Pi-Pj 的加總(公式中的sigma)。35-41行是比較尋找最小搜尋值，即為左子樹加上右子樹的平均搜尋時間，比較出最小的將存入A和R的陣列中。

演算法跑完後隨即印出陣列A(最佳平均時間)與印出陣列R(二元樹根節點)，最後再依據陣列R建立一棵樹並繪製出來。

<img src="./screenshot/img01.PNG">


### drawTree() 函式
此函式呼叫自己所建立的 BinaryTree 類別建立一棵樹並走訪(前序、中序、後序)依序顯示出來，最後再用遞迴方式將樹印出來。

<img src="./screenshot/img02.PNG">

### BinaryTree 類別
為了程式好維護與和Optimal BST演算法區隔所以我另外寫一隻JAVA類別來處理樹，以下分別說明如何建立一個Node以及新增一個Node還有使用遞迴走訪(preOrderTree、inOrderTree、postOrderTree)及印出一棵樹。


#### addNode() 新增節點
我們使用Optimal BST演算法求出來的二陣列R來建立一棵樹，第7行是建立一個新的Node，第8和9行分別使用遞迴方式建構左右子樹值到k值等於0為止。

<img src="./screenshot/img03.PNG">


#### 樹的走訪
為了驗證我所建立的樹是正確所以分別寫出前序、中序、後序走訪來驗證。

- 前序

  前序走訪先印出節點內容，再依序使用遞迴走訪左子樹再走訪右子樹

<img src="./screenshot/img04.PNG">

- 中序

  先遞迴走訪左子樹，再印出節點內容，最後再走訪右子樹

<img src="./screenshot/img05.PNG">

- 後序
  
  依序使用遞迴走訪左子樹再走訪右子樹，最後再印出節點內容。

<img src="./screenshot/img06.PNG">




### 執行與測試

- 測試一

<img src="./screenshot/img09.PNG">

驗證:
0.375*1+(0.375+0.125)*2+0.125*3=1.75

- 測試二

<img src="./screenshot/img10.PNG">

驗證:
0.2*1+(0.15+0.29)*2+(0.14+0.06+0.09)*3+0.05*4=2.15
