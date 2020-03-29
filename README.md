# MVXProject
Android MVX项目架构案例<br>
<h1>简介</h1>
本案例是分别使用MVC/MVP/MVVM的架构模式来实现RecyclerView列表加载，这也是Android中比较基础的框架设计模式<br>
<hr>
<h2>MVC</h2>
MVC全称Model View Controller，也就是模型（model）-视图（view）-控制器（controller），M是指业务模型，V是指用户界面，C则是控制器。其中 View 层其实就是程序的 UI 界面，用于向用户展示数据以及接收用户的输入，而 Model 层就是 JavaBean 实体类，用于保存实例数据，Controller 控制器用于更新 UI 界面和数据实例。<br>
![login](https://github.com/JArchie/MVXProject/raw/master/images/mvc.png)
![image]( https://github.com/JArchie/MVXProject/blob/master/images/mvc.png)
<h2>MVP</h2>
MVP模式是在MVC的基础上做了改进，最直接的思想是为了解耦。MVP是一种经典的模式，M代表Model，V代表View，P则是Presenter（Model和View之间的桥梁）。MVP模式的核心思想是把Activity中的UI逻辑抽象成View接口，把业务逻辑抽象成Presenter接口，Model类还是原来的Model类。<br>
![image](https://raw.githubusercontent.com/JArchie/MVXProject/master/images/mvp.png)
<h2>MVVM</h2>
MVVM模式包含三个部分，Model代表基本的业务逻辑，View显示内容，ViewModel将前面两者联系在一起。MVVM模式中，一个ViewModel和一个View匹配，它没有MVP中的IView接口，而是完全的和View绑定，所有View中的修改变化，都会自动更新到ViewModel中，同时ViewModel的任何变化也会自动同步到View上显示。<br>
![image]( https://github.com/JArchie/MVXProject/tree/master/images/mvvm.png)
<h3>Data Binding</h3>
2015年I/O大会上谷歌介绍了一个非常牛B的工具，这个工具可以将View和一个对象的field绑定，当field更新的时候，framework将收到通知，然后View自动更新，这个工具就是DataBinding。Android Data Binding官方原生支持MVVM模型，可以让我们在不改变现有代码的框架下，非常容易的使用这些新特性。<br>
Data Binding官方文档：<a href="https://developer.android.google.cn/topic/libraries/data-binding" target="_blank">https://developer.android.google.cn/topic/libraries/data-binding</a>
<hr>
<h2>原文地址</h2>
文章详情：<a href="https://blog.csdn.net/JArchie520/article/details/105161448" target="_blank">https://blog.csdn.net/JArchie520/article/details/105161448</a>
<h2>项目案例效果演示</h2>
![image](https://github.com/JArchie/MVXProject/tree/master/images/mvx.gif)
