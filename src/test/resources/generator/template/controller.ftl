package ${basePackage}.controller;
import ${basePackage}.common.base.BaseResult;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add")
    public BaseResult<String> add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
         return new BaseResult<String>();
    }

    @PostMapping("/delete")
    public BaseResult<String> delete(@RequestParam Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return new BaseResult<String>();
    }

    @PostMapping("/update")
    public BaseResult<String> update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
         return new BaseResult<String>();
    }

    @PostMapping("/detail")
    public BaseResult<${modelNameUpperCamel}> detail(@RequestParam Integer id) {
        return new BaseResult<${modelNameUpperCamel}>(${modelNameLowerCamel}Service.findById(id));
    }

    @PostMapping("/list")
    public BaseResult<${modelNameUpperCamel}> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<>(list);
        return new BaseResult<${modelNameUpperCamel}>(pageInfo);
    }
}
