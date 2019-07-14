 <html dir='rtl'>


<head>
  <style>
    body {
            background-color: rgb(26, 116, 26);
            padding-top: 50px;
            font-family: tahoma;
        }
        table {
            width: 800px !important;
            height: 1000px;
            margin: 0 auto !important;
            background-color: #efefef;
            border-radius: 9px;
            font-size: 1.4em;
        }
        td {
            border-radius: 5px;
            border: 2px solid #efefef;
            text-align: center;
            padding: 7px;
        }
        tr {
            background-color: #d6d6d6;
        }
        .header {
            background-color: #b3b2b2;
        }
        .big_header {
            background-color: rgb(0, 0, 0);
            font-weight: bolder;
            color: rgb(192, 190, 190);
        }
        .med_header {
            background-color: #979696;
        }
        .alli_identifier {
            background-color: #E34234; 
            font-size: 1.5em;
            font-weight: bolder;
        }
        button {
            background-color: #efefef;
            float: left;
        }
        .buttonNext {
            float: left;
            width: 150px;
            font-size: 1.2em !important;
        }
        .buttonBack {
            float: right;
            width: 150px;
            font-size: 1.2em !important;
        }
    </style>
</head>

<body>
  <script type='text/javascript'>
var path = window.location.pathname;
    var tableName = path.substring(path.lastIndexOf('/') + 1);
    var count = tableName.match(/\d/);
    function next() {
      if (count<=5){
      count++;
      open('table' + count + '.html', '_self');
        }
    }
    function back() {
      if (count>=2){
      count--;
      open('table' + count + '.html', '_self');
        }
    }
  </script>
  <table>
    <tr>
      <td class='big_header' colspan='3'>מספר קבוצה</td>
      <td class='big_header' colspan='3'>מספר משחק</td>
    </tr>
    <tr>
      <td class='alli_identifier' colspan='3'>5654</td>
      <td class='alli_identifier' colspan='3'>59</td>
    </tr>
    <tr>
      <td class='big_header' colspan='6'>אוטונומי</td>
    </tr>
    <div>
      <tr>
        <td class='med_header' colspan='3'>קרגו</td>
        <td class='med_header' colspan='3'>טיל</td>
      </tr>
      <tr>
        <td class='header'>מיקום</td>
        <td class='header'>כדור</td>
        <td class='header'>דיסק</td>
        <td class='header'>מיקום</td>
        <td class='header'>כדור</td>
        <td class='header'>דיסק</td>
      </tr>
      </td>
      <tbody>
        <tr>
          <td class='header'>אמצע</td>
          <td>-</td>
          <td>5654</td>
          <td class='header'>קרוב</td>
          <td>5654</td>
          <td>5654</td>
        </tr>
        <tr>
          <td class='header'>צד</td>
          <td>5654</td>
          <td>5654</td>
          <td class='header'>רחוק</td>
          <td>5654</td>
          <td>5654</td>
        </tr>
      </tbody>
    </div>
    <td class='big_header' colspan='6'>טלאופ</td>
    <tr>
      <td class='header' colspan='2'>מיקום</td>
      <td class='header' colspan='2'>כדור</td>
      <td class='header' colspan='2'>דיסק</td>
    </tr>
    </td>
    <tbody>
      <tr>
        <td class='header' colspan='2'>גבוה</td>
        <td colspan = '2'> 5654 </td>
        <td colspan='2'>5654</td>
      </tr>
      <tr>
        <td class='header' colspan='2'>בינוני</td>
        <td colspan = '2'> 5654 </td>
        <td colspan='2'>5654</td>
      </tr>
      <tr>
        <td class='header' colspan='2'>נמוך</td>
        <td colspan = '2'> 5654 </td>
        <td colspan='2'>5654</td>
      </tr>
      <tr>
        <td class='header' colspan='2'>קרגו אמצע</td>
        <td colspan = '2'> 5654 </td>
        <td colspan= '2'> 5654 </td>
      </tr>
      <tr>
        <td class='header' colspan='2'>סה'כ</td>
        <td colspan = '2'> 5654 </td>
        <td colspan='2'>5654</td>
      </tr>
    </tbody>
    <tr>
      <td class='big_header' colspan='6'>טיפוס</td>
    </tr>
    <tr>
      <td class='header' colspan='2'>שלב 1</td>
      <td class='header' colspan='2'>שלב 2</td>
      <td class='header' colspan='2'>שלב 3</td>
    </tr>
    </td>
    <tbody>
      <tr>
        <td colspan = '2'> 5654 </td>
        <td colspan='2'>5654</td>
        <td colspan = '2'> 5654 </td>
      </tr>
    </tbody>
    <tr>
      <td class='big_header' colspan='6'>אחר</td>
    </tr>
    <tr>
      <td class='header' colspan='3'>הגנה</td>
      <td class='header' colspan='3'>נפלה דיסקית</td>
    </tr>
    </td>
    <tbody>
      <tr>
        <td colspan = '3'>
          <div>
          </div>
        </td>
        <td colspan= '3'> 5654 </td>
      </tr>
      <tr>
        <td class='big_header' colspan='6'>הערות</td>
      </tr>
      <tr>
        <td colspan = '6'> הערות </td>
      </tr>
    </tbody>
    <tr>
      <td colspan='6'>
        <button class='button buttonNext' onclick='next()'>הבא</button>
        <button class='button buttonBack' onclick='back()'>הקודם</button>
      </td>
    </tr>
  </table>
</body>

</html>