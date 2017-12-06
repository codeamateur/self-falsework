package ${basePackage}.controller;
import ${basePackage}.common.base.BaseResult;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public BaseResult<String> add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return new BaseResult<String>();
    }

    @DeleteMapping("/{id}")
    public BaseResult<String> delete(@PathVariable Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return new BaseResult<String>();
    }

    @PutMapping
    public BaseResult<String> update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return new BaseResult<String>();
    }

    @GetMapping("/{id}")
    public BaseResult<${modelNameUpperCamel}> detail(@PathVariable Integer id) {
       return new BaseResult<${modelNameUpperCamel}>(${modelNameLowerCamel}Service.findById(id));
    }

    @GetMapping
    public BaseResult<${modelNameUpperCamel}> list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<>(list);
        return new BaseResult<${modelNameUpperCamel}>(pageInfo);
    }
}
