一、想法：
1.将项目开发为android学习的App。
    功能：知识点(博客，示例，个人demo，个人总结)
    注意点：
    博客（网址+本地存储，防止网站变化或过期）

2.将自己所学应用到项目中。

3.项目模块化，抽取公共模块，通过修改界面能快速构建不同的app，减少研发周期。


二、项目总结
1.数据模块
a、数据的访问都是通过缓存数据，不要直接访问数据库或者SP等等。
  原因：每次直接访问是耗时的，影响性能。通过缓存数据。一次访问，多处引用，降低了直接访问的次数。
  注意点：注意缓存与数据源的同步。尽量遵循单一原则。

b、缓存与数据源的同步思考
   1.获取数据使用缓存。
   2.数据源发生变化时，更新获取数据的缓存。
   3.使用观察者模式，数据源变化与获取数据形成观察者模式
  示例：room的观察者模式，state的观察者模式。

2.UI模块
a、页面之间的跳转和切换使用Navigation导航栏，减少Activity的使用

3.逻辑思维
a、当一个状态多次变化，怎么在另个界面反馈？
   1.回调，观察者，对应每次变化。
   2.状态添加内添加版本号，版本号变化时更新。
   3.只接收最新的状态变化。
   实例：LiveData，Flow.xxxxLastest操作符。

b.一处修改，怎么反馈在其他地方？
    1.反馈的地方重新加载数据。例如：启动Activity
    2.反馈的地方已经显示，需要重新更新。例如：Compose中的State，LivaData，RxBus（EventBus），其他RxJava，但没有感知生命周期。

4.功能状态的切换，考虑点（例如简版模式与完整版模式）
    1.切换时，不同状态的变化。特别是显示，数据重新获取。
    2.切换后，初始化的变化

三、相关知识点
1.build.gradle.kts的使用
2.Compose与View的交互
3.Room的使用
4.Paging的使用
=================
5.依赖抽象为最外层配置，model引用该配置，更容易维护

6.Compose总结
a.禁止在Compose可组合函数中调用业务逻辑（例如启动Activity，Service），可组合函数中只处理界面相关内容。
原因：可组合函数的周期性和属性，例如重组不可控等等

b.非Compose调用Compose处理
例如：点击button调整页面逻辑
    a、非Compose修改状态，通过状态处理Compose
    b、使用navigation



四、引入相关示例
1.todoapp
architecture-samples branch:main
https://github.com/android/architecture-samples
时间：2023.9.8
注意：测试部分缺失

五、编码总结
1.kotlin中缓存数据的构建，模仿：ViewModelLazy中value
示例：Test1中getUserInfo方法

2.构建委托属性，模仿ViewModel的委托

=======================================================================
委托模式
ViewModel
Room
架构部分
Lifecycle系列
IOC
Paging
Navigation
架构
模块化，组件化




