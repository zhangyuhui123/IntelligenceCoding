export const JUDGE_STATUS = {
  '-10': {
    name: 'Not Submitted',
    short: 'NS',
    color: 'gray',
    type: 'info',
    rgb:'#909399'
  },
  '-3': {
    name: 'Presentation Error',
    short: 'PE',
    color: 'yellow',
    type: 'warning',
    rgb:'#f90'
  },
  '-2': {
    name: 'Compile Error',
    short: 'CE',
    color: 'yellow',
    type: 'warning',
    rgb:'#f90'
  },
  '-1': {
    name: 'Wrong Answer',
    short: 'WA',
    color: 'red',
    type: 'error',
    rgb:'#ed3f14'
  },
  '0': {
    name: 'Accepted',
    short: 'AC',
    color: 'green',
    type: 'success',
    rgb:'#19be6b'
  },
  '1': {
    name: 'Time Limit Exceeded',
    short: 'TLE',
    color: 'red',
    type: 'error',
    rgb:'#ed3f14'
  },
  '2': {
    name: 'Memory Limit Exceeded',
    short: 'MLE',
    color: 'red',
    type: 'error',
    rgb:'#ed3f14'
  },
  '3': {
    name: 'Runtime Error',
    short: 'RE',
    color: 'red',
    type: 'error',
    rgb:'#ed3f14'
  },
  '4': {
    name: 'System Error',
    short: 'SE',
    color: 'gray',
    type: 'info',
    rgb:'#909399'
  },
  '5': {
    name: 'Pending',
    color: 'yellow',
    type: 'warning',
    rgb:'#f90'
  },
  '6':{
    name: 'Compiling',
    short: 'CP',
    color: 'green',
    type: 'info',
    rgb:'#25bb9b'
  },
  '7': {
    name: 'Judging',
    color: 'blue',
    type: '',
    rgb:'#2d8cf0'
  },
  '8': {
    name: 'Partial Accepted',
    short: 'PAC',
    color: 'blue',
    type: '',
    rgb:'#2d8cf0'
  },
  '9': {
    name: 'Submitting',
    color: 'yellow',
    type: 'warning',
    rgb:'#f90'
  },
  '10':{
    name:"Submitted Failed",
    color:'gray',
    short:'SF',
    type: 'info',
    rgb:'#909399',
  }
}

export const JUDGE_STATUS_RESERVE={
  'pe':-3,
  'ce':-2,
  'wa':-1,
  'ac':0,
  'tle':1,
  'mle':2,
  're':3,
  'se':4,
  'Compiling':5,
  'Pending':6,
  'Judging':7,
  'sf':10,
}

export const PROBLEM_LEVEL={
  '0':{
    name:'Easy',
    color:'green'
  },
  '1':{
    name:'Mid',
    color:'blue'
  },
  '2':{
    name:'Hard',
    color:'red'
  }
}

export const PROBLEM_LEVEL_RESERVE={
  'Easy':0,
  'Mid': 1,
  'Hard':2,
}


export const REMOTE_OJ = [
  {name:'HDU',key:"HDU"},
  {name:"Codeforces",key:"CF"}
]

export const CONTEST_STATUS = {
  'SCHEDULED': -1,
  'RUNNING': 0,
  'ENDED': 1
}

export const CONTEST_STATUS_REVERSE = {
  '-1': {
    name: 'Scheduled',
    color: '#f90'
  },
  '0': {
    name: 'Running',
    color: '#19be6b'
  },
  '1': {
    name: 'Ended',
    color: '#ed3f14'
  }
}

export const RULE_TYPE = {
  ACM: 0,
  OI: 1
}

export const CONTEST_TYPE_REVERSE = {
  '0': {
    name:'Public',
    color:'success',
    tips:'Public_Tips',
    submit:true,              // 公开赛可看可提交
    look:true,
  },
  '1':{
    name:'Private',
    color:'danger',
    tips:'Private_Tips',
    submit:false,         // 私有赛 必须要密码才能看和提交
    look:false,
  },
  '2':{
    name:'Protected',
    color:'warning',
    tips:'Protected_Tips',
    submit:false,       //保护赛，可以看但是不能提交，提交需要附带比赛密码
    look:true,
  }
}

export const CONTEST_TYPE = {
  PUBLIC: 0,
  PRIVATE: 1,
  PROTECTED: 2
}

export const USER_TYPE = {
  REGULAR_USER: 'user',
  ADMIN: 'admin',
  SUPER_ADMIN: 'root'
}


export const STORAGE_KEY = {
  AUTHED: 'authed',
  PROBLEM_CODE: 'hojProblemCode',
  languages: 'languages'
}

export function buildProblemCodeKey (problemID, contestID = null) {
  if (contestID) {
    return `${STORAGE_KEY.PROBLEM_CODE}_${contestID}_${problemID}`
  }
  return `${STORAGE_KEY.PROBLEM_CODE}_NoContest_${problemID}`
}

