# easyapi


## feature

## config
```
easyapi:
    enable: true #是否开启自动生成接口,默认开启
    projectName: easy-es # 项目名称,填你的项目名称,用于生成的接口文档界面展示
    projectPath: E:\\easy-es\\sample # 项目路径,填你的项目路径,生成时扫描的目录,默认为当前项目路径, controller分散在多个子模块的项目可以用逗号隔开
    docVersion: 1.0.0 # 生成接口文档的版本号
    docPath: E:\\tmp # 生成的文档路径,例如我想把生成的文档放在E盘tmp文件夹下 ,不配置则默认在当前工程目录下生成
    autoGenerate: true # 是否自动生成 默认为true 若为false时,需要加@DocApi注解 可用最细粒度于控制生成的接口
    generateMarkDown: true # 是否生成markdown文档 默认为true, 为false则仅生成html接口文档
    watermark: easyapi # 水印,默认值为easyapi
    locale: Locale.CHINESE # 生成文档的语言,默认为当前系统默认语言,当前已支持中文和英文接口文档,已实现i18n
```