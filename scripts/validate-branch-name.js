#!/usr/bin/env node
import * as ChildProcess from 'node:child_process'
import * as process from 'node:process'

const checkValidBranchName = () => {
  console.warn('Check VALID_BRANCH_NAME')

  const currentBranchName = ChildProcess.execSync('git symbolic-ref --short HEAD')
    .toString()
    .trim()

  const regex = /^(feature|fix|hotfix|release)\/.+$/g
  const found = currentBranchName.match(regex) !== null
  process.exit(found ? 0 : 1)
}

checkValidBranchName()
