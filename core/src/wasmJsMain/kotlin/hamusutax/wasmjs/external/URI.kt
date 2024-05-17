@file:Suppress("UNUSED")
package hamusutax.wasmjs.external

/**
 * `eval()` 是全局对象的一个函数属性。
 *
 * `eval()` 的参数是一个字符串。如果字符串表示的是表达式，`eval()` 会对表达式进行求值。如果参数表示一个或多个 JavaScript 语句，那么 `eval()` 就会执行这些语句。不需要用 `eval()` 来执行一个算术表达式：因为 JavaScript 可以自动为算术表达式求值。
 *
 * 如果你以字符串的形式构造了算术表达式，那么可以在后面用 `eval()` 对它求值。例如，假设你有一个变量 x，你可以通过将表达式的字符串值（例如 `3 * x + 2`）赋值给一个变量，然后在你的代码后面的其他地方调用 `eval()`，来推迟涉及 `x` 的表达式的求值。
 *
 * 如果 `eval()` 的参数不是字符串， `eval()` 会将参数原封不动地返回。在下面的例子中，`String` 构造器被指定，而 `eval()` 返回了 String 对象而不是执行字符串。
 *
 * ```js
 * eval(new String("2 + 2")); // 返回了包含"2 + 2"的字符串对象
 * eval("2 + 2"); // returns 4
 * ```
 *
 * 你可以使用一些通用的方法来绕过这个限制，例如使用 toString()。
 *
 * ```js
 * var expression = new String("2 + 2");
 * eval(expression.toString());
 * ```
 *
 * 如果你间接的使用 eval()，比如通过一个引用来调用它，而不是直接的调用 eval。从 ECMAScript 5 起，它工作在全局作用域下，而不是局部作用域中。这就意味着，例如，下面的代码的作用声明创建一个全局函数，并且 eval 中的这些代码在执行期间不能在被调用的作用域中访问局部变量。
 *
 * ```js
 * function test() {
 *   var x = 2,
 *     y = 4;
 *   console.log(eval("x + y")); // 直接调用，使用本地作用域，结果是 6
 *   var geval = eval; // 等价于在全局作用域调用
 *   console.log(geval("x + y")); // 间接调用，使用全局作用域，throws ReferenceError 因为 `x` 未定义
 *   (0, eval)("x + y"); // 另一个间接调用的例子
 * }
 * ```
 */
external fun eval(url: String): JsAny

/**
 * isFinite 是全局的方法，不与任何对象有关系。
 *
 * 你可以用这个方法来判定一个数字是否是有限数字。`isFinite` 方法检测它参数的数值。
 * 如果参数是 `NaN`，正无穷大或者负无穷大，会返回 `false`，其他返回 `true`。
 *
 * @param testValue 用于检测有限性（finiteness）的值。
 * @see [isFinite\(\) - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/isFinite)
 */
external fun isFinite(testValue: JsAny)

/**
 * `isNaN()` 是全局对象的一个函数属性。
 *
 * 对于数字值，`isNaN()` 检测该值是否为 `NaN` 值。
 * 当 isNaN() 函数的参数不是数字类型时，其会首先被转换为数字，然后将其结果值与 NaN 进行比较。
 *
 * `isNaN()` 对于非数字参数的行为可能会令人困惑！
 * 例如，空字符串被强制转换为 `0`，布尔值被强制转换为 `0` 或 `1`；
 * 直观上，两者均“不是数字”，仅因它们的运算结果不是 NaN，而使得 `isNaN()` 返回 `false`。
 * 因此，`isNaN()` 既不回答“输入是否为浮点数值 `NaN`”，也不回答“输入是否为数字”这两个问题。
 *
 * `Number.isNaN()` 是检测一个值是否为数字值 `NaN` 的更可靠的方法。
 * 或者，也可以使用表达式 `x !== x`，这两种方法都不会产生全局 `isNaN()` 不可靠的误判。
 * 要检测一个值是否为数字，请使用 `typeof x === "number"`。
 *
 * `isNaN()` 函数回答的问题是“在数字上下文中，输入是否在功能上与 `NaN` 等价”。
 * 如果 `isNaN(x)` 返回 `false`，则可以在算术表达式中使用 `x`，就好像它是一个有效的数字，而不是 `NaN`。
 * 如果 `isNaN(x)` 返回 `true`，则 x 将被强制转换为 `NaN`，并使大多数算术表达式返回 `NaN`（因为 `NaN` 会传播）。
 * 例如，可以使用这种方法来测试函数的参数是否可以进行算术处理（像数字一样使用），并通过抛出错误、提供默认值等来处理不是数字的值。
 * 这样，就可以拥有一个可以利用 JavaScript 提供的全部灵活性的函数，该函数可以根据上下文隐式地转换值。
 *
 * @param value 要被检测的值。
 * @return 如果给定值在被转换为数字后为 `NaN` 则返回值为 `true`；否则为 `false`。
 * @see [isNaN() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/isNaN)
 */
external fun isNaN(value: JsAny)


/**
 * 将已编码 URI 中所有能识别的转义序列转换成原字符，但不能解码那些不会被 [encodeURI] 编码的内容（例如 "#"）
 *
 * @param encodedURI 一个完整的编码过的 URI
 * @return 返回一个给定编码统一资源标识符 (URI) 的未编码版本的新字符串。
 * @throws Exception 当 [encodedURI] 包含无效字符序列时，引发 `URIError`（“格式错误的 URI 序列”）异常。
 * @see [decodeURI() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/decodeURI)
 */
external fun decodeURI(encodedURI: String): String

/**
 * 将已编码 URI 中所有能识别的转义序列转换成原字符。
 *
 * @param encodedURI 编码后的部分 URI
 * @return 一个解码后的统一资源标识符（URI）字符串，处理前的 URI 经过了给定格式的编码。
 * @throws Exception 当该方法使用不当时，将会抛出一个 `URIError`（“格式错误的 URI 序列”）异常。
 * @see [decodeURIComponent() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/decodeURIComponent)
 */
external fun decodeURIComponent(encodedURI: String): String

/**
 * 假定一个 URI 是完整的 URI，那么无需对那些保留的并且在 URI 中有特殊意思的字符进行编码。
 * ```
 * http://username:password@www.example.com:80/path/to/file.php?foo=316&bar=this+has+spaces#anchor
 * ```
 * [encodeURI] 会替换所有的字符，但不包括以下字符，即使它们具有适当的 `UTF-8` 转义序列：
 *
 * | 类型        | 包含                                        |
 * | :--------- | :------------------------------------------ |
 * | 保留字符     |`;` `,` `/` `?` `:` `@` `&` `=` `+` `$`     |
 * | 非转义的字符 | 字母 数字 `-` `_` `.` `!` `~` `*` `'` `(` `)` |
 * | 数字符号    | `#`                                         |
 *
 * 请注意，[encodeURI] 自身无法产生能适用于 HTTP GET 或 POST 请求的 URI，
 * 例如对于 `XMLHTTPRequests`，因为 "&", "+", 和 "=" 不会被编码，然而在 GET 和 POST 请求中它们是特殊字符。
 * 然而 [encodeURIComponent] 这个方法会对这些字符编码。
 *
 * 另外，如果试图编码一个非高 - 低位完整的代理字符，将会抛出一个 `URIError` 错误，例如：
 *
 * ```js
 * // 编码高 - 低位完整字符 ok
 * console.log(encodeURI("\uD800\uDFFF"));
 *
 * // 编码单独的高位字符抛出 "Uncaught URIError: URI malformed"
 * console.log(encodeURI("\uD800"));
 *
 * // 编码单独的低位字符抛出 "Uncaught URIError: URI malformed"
 * console.log(encodeURI("\uDFFF"));
 * ```
 *
 * 并且需要注意，如果 URL 需要遵循较新的RFC3986标准，那么方括号是被保留的 (给 IPv6)，
 * 因此对于那些没有被编码的 URL 部分 (例如主机)，可以使用下面的代码：
 *
 * ```js
 * function fixedEncodeURI(str) {
 *   return encodeURI(str).replace(/%5B/g, "[").replace(/%5D/g, "]");
 * }
 * ```
 *
 * @param uri 一个完整的 URI。
 * @return 一个新字符串，表示提供的字符串编码为统一资源标识符 (URI)。
 * @see [encodeURI() - JavaScript | MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/encodeURI)
 */
external fun encodeURI(uri: String): String

/**
 * [encodeURIComponent] 转义除了如下所示外的所有字符：
 *
 * ```
 * 不转义的字符：
 *     A-Z a-z 0-9 - _ . ! ~ * ' ( )
 * ```
 *
 * [encodeURIComponent] 和 [encodeURI] 有以下几个不同点：
 *
 * ```js
 * var set1 = ";,/?:@&=+$"; // 保留字符
 * var set2 = "-_.!~*'()"; // 不转义字符
 * var set3 = "#"; // 数字标志
 * var set4 = "ABC abc 123"; // 字母数字字符和空格
 *
 * console.log(encodeURI(set1)); // ;,/?:@&=+$
 * console.log(encodeURI(set2)); // -_.!~*'()
 * console.log(encodeURI(set3)); // #
 * console.log(encodeURI(set4)); // ABC%20abc%20123 (空格被编码为 %20)
 *
 * console.log(encodeURIComponent(set1)); // %3B%2C%2F%3F%3A%40%26%3D%2B%24
 * console.log(encodeURIComponent(set2)); // -_.!~*'()
 * console.log(encodeURIComponent(set3)); // %23
 * console.log(encodeURIComponent(set4)); // ABC%20abc%20123 (空格被编码为 %20)
 * ```
 *
 * 注意，如果试图编码一个非高 - 低位完整的代理字符，将会抛出一个 `URIError` 错误，例如：
 *
 * ```js
 * // 高低位完整
 * alert(encodeURIComponent("\uD800\uDFFF"));
 *
 * // 只有高位，将抛出"URIError: malformed URI sequence"
 * alert(encodeURIComponent("\uD800"));
 *
 * // 只有低位，将抛出"URIError: malformed URI sequence"
 * alert(encodeURIComponent("\uDFFF"));
 * ```
 *
 * 为了避免服务器收到不可预知的请求，对任何用户输入的作为 URI 部分的内容你都需要用 [encodeURIComponent] 进行转义。
 * 比如，一个用户可能会输入"`Thyme &time=again`"作为 `comment` 变量的一部分。
 * 如果不使用 [encodeURIComponent] 对此内容进行转义，服务器得到的将是 `comment=Thyme%20&time=again`。
 * 请注意，"&"符号和"="符号产生了一个新的键值对，所以服务器得到两个键值对
 * （一个键值对是 `comment=Thyme`，另一个则是 `time=again`），而不是一个键值对。
 *
 * 对于 [`application/x-www-form-urlencoded`](https://www.whatwg.org/specs/web-apps/current-work/multipage/association-of-controls-and-forms.html#application/x-www-form-urlencoded-encoding-algorithm) (POST)
 * 这种数据方式，空格需要被替换成 '+'，所以通常使用 [encodeURIComponent] 的时候还会把 "%20" 替换为 "+"。
 *
 * 为了更严格的遵循 [RFC 3986](https://datatracker.ietf.org/doc/html/rfc3986)
 * （它保留 !, ', (, ), 和 *），即使这些字符并没有正式划定 URI 的用途，下面这种方式是比较安全的：
 *
 * ```js
 * function fixedEncodeURIComponent(str) {
 *   return encodeURIComponent(str).replace(/[!'()*]/g, function (c) {
 *     return "%" + c.charCodeAt(0).toString(16).toUpperCase();
 *   });
 * }
 * ```
 *
 * @param uriComponent 一个 string、number、boolean、null，undefined 或者任何 object。在编码之前，uriComponent 参数会被转化为字符串。
 * @return 原字串作为 URI 组成部分被被编码后的新字符串。
 */
external fun encodeURIComponent(uriComponent: String): String
